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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofSuccess;
import static com.wooread.wooreadbase.tools.MessageTools.message;

@Service
@Transactional
public class NovelHeadServiceImpl implements NovelHeadService {

    @Resource(name = "NovelHead")
    private CommonRepository<NovelHead, Integer> novelHeadCommonRepository;

    @Resource(name = "NovelChapter")
    private CommonRepository<NovelChapter, Integer> novelChapterCommonRepository;

    @Autowired
    private UserService userService;

    @Override
    public BaseServiceOutput<NovelHead> createNovelHead(NovelHeadServiceInput.CreateNovelHeadInput input) {
        if (novelHeadCommonRepository.findAll(Specifications.equal(
                "novelName", input.getNovelName())).size() > 0) {
            return ofFail(message("duplicate", "novel"));
        }
        if (!userService.existUser(input.getUserId()).getData()) {
            return ofFail(message("no-such", "author"));
        }
        return ofSuccess(() -> {
            NovelHead novelHead = new NovelHead();
            BeanUtils.copyProperties(input, novelHead);
            return novelHeadCommonRepository.save(novelHead);
        });
    }

    @Override
    public BaseServiceOutput<NovelHead> updateNovelHead(NovelHeadServiceInput.UpdateNovelHeadInput input) {
        if (novelHeadCommonRepository.findAll(Specifications.equal("novelName", input.getNovelName())).size() > 0) {
            return ofFail(message("duplicate", "novel name"));
        }
        if (!userService.existUser(input.getUserId()).getData()) {
            return ofFail(message("no-such", "author"));
        }
        return novelHeadCommonRepository.findById(input.getNovelId()).map(novelHead -> {
            return ofSuccess(() -> {
                BeanUtils.copyProperties(input, novelHead);
                return novelHeadCommonRepository.save(novelHead);
            });
        }).orElse(ofFail(message("no-such", "novel head")));
    }

    @Override
    public BaseServiceOutput<Boolean> deleteNovelHead(Integer novelId) {
        return novelHeadCommonRepository.findById(novelId).map(novelHead -> {
            return ofSuccess(() -> {
                novelHeadCommonRepository.delete(novelHead);
                novelChapterCommonRepository.deleteInBatch(novelChapterCommonRepository.findAll(Specifications.equal("novelId", novelId)));
                return true;
            });
        }).orElse(ofFail(message("no-such", "novel"), false));
    }

    @Override
    public BaseServiceOutput<Page<NovelHead>> findByLikeName(String name, Pageable pageable) {
        String likeName = "%" + name + "%";
        return ofSuccess(() -> novelHeadCommonRepository.findAll(Specifications.like("novelName", likeName), pageable));
    }
}
