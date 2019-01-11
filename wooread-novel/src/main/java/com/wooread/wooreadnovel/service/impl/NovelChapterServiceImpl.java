package com.wooread.wooreadnovel.service.impl;

import com.wooread.wooreadnovel.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelChapterServiceInput;
import com.wooread.wooreadnovel.model.NovelChapter;
import com.wooread.wooreadnovel.model.NovelHead;
import com.wooread.wooreadnovel.service.NovelChapterService;
import cui.shibing.commonrepository.CommonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.wooread.wooreadnovel.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadnovel.dto.BaseServiceOutput.ofSuccess;
import static com.wooread.wooreadnovel.tools.MessageTools.message;

@Service
@Transactional
public class NovelChapterServiceImpl implements NovelChapterService {

    @Resource(name = "NovelChapter")
    private CommonRepository<NovelChapter, Integer> novelChapterCommonRepository;

    @Resource(name = "NovelHead")
    private CommonRepository<NovelHead, Integer> novelHeadCommonRepository;

    @Override
    public BaseServiceOutput<NovelChapter> createNovelChapter(NovelChapterServiceInput.CreateNovelChapterInput input) {
        return novelHeadCommonRepository.findById(input.getNovelId()).map(novelHead -> {
            return ofSuccess(() -> {
                NovelChapter novelChapter = new NovelChapter();
                BeanUtils.copyProperties(input, novelChapter);
                return novelChapterCommonRepository.save(novelChapter);
            });
        }).orElse(ofFail(message("no-such", "novel")));
    }

    @Override
    public BaseServiceOutput<NovelChapter> updateNovelChapter(NovelChapterServiceInput.UpdateNovelChapterInput input) {
        return novelChapterCommonRepository.findById(input.getChapterId()).map(novelChapter -> {
            return ofSuccess(() -> {
                BeanUtils.copyProperties(input, novelChapter);
                return novelChapterCommonRepository.save(novelChapter);
            });
        }).orElse(ofFail(message("no-such", "chapter")));
    }

    @Override
    public BaseServiceOutput<NovelChapter> findNovelChapterByChapterId(Integer chapterId) {
        return ofSuccess(() -> novelChapterCommonRepository.findById(chapterId).orElse(null));
    }
}
