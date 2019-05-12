package com.wooread.wooreadnovel.service.impl;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelHeadServiceInput;
import com.wooread.wooreadnovel.model.NovelChapter;
import com.wooread.wooreadnovel.model.NovelHead;
import com.wooread.wooreadnovel.service.NovelHeadService;
import com.wooread.wooreadnovel.service.UserService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;

import java.util.Collection;
import java.util.List;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofSuccess;
import static com.wooread.wooreadbase.tools.MessageTools.message;
import static com.wooread.wooreadnovel.message.Msg.NovelHeadMsg.*;
import static cui.shibing.commonrepository.Specifications.equal;
import static cui.shibing.commonrepository.Specifications.like;

@Service
@Transactional
public class NovelHeadServiceImpl implements NovelHeadService {

    @Resource(name = "NovelHead")
    private CommonRepository<NovelHead, String> novelHeadCommonRepository;

    @Resource(name = "NovelChapter")
    private CommonRepository<NovelChapter, String> novelChapterCommonRepository;

    @Autowired
    private UserService userService;

    @Override
    public BaseServiceOutput<NovelHead> createNovelHead(NovelHeadServiceInput.CreateNovelHeadInput input) {
        if (novelHeadCommonRepository.findAll(equal(
                "novelName", input.getNovelName())).size() > 0) {
            return ofFail(message(DUPLICATE_NOVEL.toString(), input.getNovelName()));
        }
        return userService.existUser(input.getUserId()).ifSuccess(isExist -> {
            if (isExist) {
                NovelHead novelHead = new NovelHead();
                BeanUtils.copyProperties(input, novelHead);
                try {
                    return ofSuccess(novelHeadCommonRepository.save(novelHead));
                } catch (Exception e) {
                    // 捕捉重复小说名
                    return ofFail(message(DUPLICATE_NOVEL.toString(), input.getNovelName()), (NovelHead) null);
                }
            } else {
                return ofFail(message(NO_SUCH_CREATOR.toString(), input.getUserId()), (NovelHead) null);
            }
        }).orElse(ofFail("error"));
    }

    @Override
    public BaseServiceOutput<NovelHead> updateNovelHead(NovelHeadServiceInput.UpdateNovelHeadInput input) {
        if (novelHeadCommonRepository.findAll(equal("novelName", input.getNovelName())).size() > 0) {
            return ofFail(message(NO_SUCH_NOVEL.toString(), input.getNovelName()));
        }
        if (!userService.existUser(input.getUserId()).getPayload()) {
            return ofFail(message(NO_SUCH_CREATOR.toString(), input.getUserId()));
        }
        return novelHeadCommonRepository.findById(input.getNovelId()).map(novelHead -> ofSuccess(() -> {
            BeanUtils.copyProperties(input, novelHead);
            return novelHeadCommonRepository.save(novelHead);
        })).orElse(ofFail(message(NO_SUCH_NOVEL.toString(), input.getNovelName())));
    }

    @Override
    public BaseServiceOutput<Boolean> deleteNovelHead(String novelId) {
        return novelHeadCommonRepository.findById(novelId).map(novelHead -> {
            return ofSuccess(() -> {
                novelHeadCommonRepository.delete(novelHead);
                novelChapterCommonRepository.deleteInBatch(novelChapterCommonRepository.findAll(equal("novelId", novelId)));
                return true;
            });
        }).orElse(ofFail(message(NO_SUCH_NOVEL.toString(), novelId), false));
    }

    @Override
    public BaseServiceOutput<Page<NovelHead>> listNovelHeads(NovelHeadServiceInput.QueryNovelHeadInput input, Pageable pageable) {
        Specification<NovelHead> specification = buildListNovelHeadsSpecification(input);
        return ofSuccess(()-> novelHeadCommonRepository.findAll(specification,pageable));
    }

    private Specification<NovelHead> buildListNovelHeadsSpecification(NovelHeadServiceInput.QueryNovelHeadInput input) {
        Specification<NovelHead> specification = (Specification<NovelHead>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaQuery.where().getRestriction();
        if (!StringUtils.isEmpty(input.getNovelId())) {
            specification = equal("novelId", input.getNovelId());
        }
        if (!StringUtils.isEmpty(input.getNovelName())) {
            specification = specification.and(like("novelName", "%" + input.getNovelName() + "%"));
        }
        if (!StringUtils.isEmpty(input.getClassName())) {
            specification = specification.and(equal("className", input.getClassName()));
        }
        return specification;
    }
}
