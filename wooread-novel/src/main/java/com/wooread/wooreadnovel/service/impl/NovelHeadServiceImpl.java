package com.wooread.wooreadnovel.service.impl;

import com.wooread.wooreadnovel.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelHeadServiceInput;
import com.wooread.wooreadnovel.model.NovelHead;
import com.wooread.wooreadnovel.service.NovelHeadService;
import com.wooread.wooreadnovel.service.UserService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.wooread.wooreadnovel.dto.BaseServiceOutput.CODE_FAIL;
import static com.wooread.wooreadnovel.dto.BaseServiceOutput.CODE_SUCCESS;
import static com.wooread.wooreadnovel.tools.MessageTools.message;

@Service
@Transactional
public class NovelHeadServiceImpl implements NovelHeadService {

    @Resource(name = "NovelHead")
    private CommonRepository<NovelHead, Integer> novelHeadcommonRespository;

    @Autowired
    private UserService userService;

    @Override
    public BaseServiceOutput<NovelHead> createNovelHead(NovelHeadServiceInput.CreateNovelHeadInput input) {
        if (novelHeadcommonRespository.findAll(Specifications.equal(
                "novelName", input.getNovelName())).size() > 0) {
            return new BaseServiceOutput<>(CODE_FAIL, message("duplicate", "novel"));
        }
        if (!userService.existUser(input.getAuthorId()).getData()) {
            return new BaseServiceOutput<>(CODE_FAIL, message("no-such", "author"));
        }
        return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), () -> {
            NovelHead novelHead = new NovelHead();
            BeanUtils.copyProperties(input, novelHead);
            return novelHeadcommonRespository.save(novelHead);
        });
    }

    @Override
    public BaseServiceOutput<NovelHead> updateNovelHead(NovelHeadServiceInput.UpdateNovelHeadInput input) {
        if (novelHeadcommonRespository.findAll(Specifications.equal("novelName", input.getNovelName())).size() > 0) {
            return new BaseServiceOutput<>(CODE_FAIL, message("duplicate", "novel name"));
        }
        if (!userService.existUser(input.getAuthorId()).getData()) {
            return new BaseServiceOutput<>(CODE_FAIL, message("no-such", "author"));
        }
        return novelHeadcommonRespository.findById(input.getNovelId()).map(novelHead -> {
            return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), () -> {
                BeanUtils.copyProperties(input, novelHead);
                return novelHeadcommonRespository.save(novelHead);
            });
        }).orElse(new BaseServiceOutput<>(CODE_FAIL, message("no-such", "novel head")));
    }

    @Override
    public BaseServiceOutput<Boolean> deleteNovelHead(Integer novelId) {
        return novelHeadcommonRespository.findById(novelId).map(novelHead -> {
            return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), () -> {
                novelHeadcommonRespository.delete(novelHead);
                // TODO:删除对应的章节
                return true;
            });
        }).orElse(new BaseServiceOutput<>(CODE_FAIL, message("no-such", "novel"), false));
    }

    @Override
    public BaseServiceOutput<Page<NovelHead>> findByLikeName(String name, Pageable pageable) {
        String likeName = "%" + name + "%";
        return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), () -> {
            return novelHeadcommonRespository.findAll(Specifications.like("novelName", likeName), pageable);
        });
    }
}
