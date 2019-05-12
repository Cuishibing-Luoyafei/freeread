package com.wooread.wooreadnovel.service.impl;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelChapterServiceInput;
import com.wooread.wooreadnovel.model.NovelChapter;
import com.wooread.wooreadnovel.model.NovelChapterInfo;
import com.wooread.wooreadnovel.model.NovelHead;
import com.wooread.wooreadnovel.service.NovelChapterService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofSuccess;
import static com.wooread.wooreadbase.tools.MessageTools.message;

@Service
@Transactional
public class NovelChapterServiceImpl implements NovelChapterService {

    @Resource(name = "NovelChapter")
    private CommonRepository<NovelChapter, String> novelChapterCommonRepository;

    @Resource(name = "NovelHead")
    private CommonRepository<NovelHead, String> novelHeadCommonRepository;

    @Resource(name = "NovelChapterInfo")
    private CommonRepository<NovelChapterInfo, String> chapterInfoCommonRepository;

    @Override
    public BaseServiceOutput<NovelChapter> createNovelChapter(
            NovelChapterServiceInput.CreateNovelChapterInput input) {
        return novelHeadCommonRepository.findById(input.getNovelId()).map(novelHead -> ofSuccess(() -> {
            NovelChapter novelChapter = new NovelChapter();
            BeanUtils.copyProperties(input, novelChapter);
            novelChapterCommonRepository.save(novelChapter);

            NovelChapterInfo chapterInfo = new NovelChapterInfo();
            BeanUtils.copyProperties(input, chapterInfo);

            chapterInfo.setChapterId(novelChapter.getChapterId());
            chapterInfo.setWordCount(input.getContent().length());

            chapterInfoCommonRepository.save(chapterInfo);
            return novelChapter;
        })).orElse(ofFail(message("no-such", "novel")));
    }

    @Override
    public BaseServiceOutput<NovelChapter> updateNovelChapter(
            NovelChapterServiceInput.UpdateNovelChapterInput input) {
        return novelChapterCommonRepository.findById(input.getChapterId()).map(novelChapter -> {
            Optional<NovelChapterInfo> chapterInfo = chapterInfoCommonRepository.findOne(Specifications.equal("chapterId", novelChapter.getChapterId()));
            if (!chapterInfo.isPresent())
                return ofFail(message("no-such", "chapter info"), (NovelChapter) null);
            else {
                chapterInfo.get().setWordCount(input.getContent().length());
                chapterInfoCommonRepository.save(chapterInfo.get());
            }
            return ofSuccess(() -> {
                BeanUtils.copyProperties(input, novelChapter);
                return novelChapterCommonRepository.save(novelChapter);
            });
        }).orElse(ofFail(message("no-such", "chapter")));
    }

    @Override
    public BaseServiceOutput<NovelChapter> findNovelChapterByChapterId(String chapterId) {
        return ofSuccess(novelChapterCommonRepository.findById(chapterId).orElse(null));
    }
}
