package com.wooread.wooreadnovel.service.impl;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelChapterInfoServiceInput;
import com.wooread.wooreadnovel.model.NovelChapterInfo;
import com.wooread.wooreadnovel.model.NovelHead;
import com.wooread.wooreadnovel.service.NovelChapterInfoService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.beans.BeanUtils;
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
public class NovelChapterInfoServiceImpl implements NovelChapterInfoService {

    @Resource(name = "NovelChapterInfo")
    private CommonRepository<NovelChapterInfo, String> chapterInfoCommonRepo;

    @Resource(name = "NovelHead")
    private CommonRepository<NovelHead,String> novelHeadCommonRepository;

    @Override
    public BaseServiceOutput<NovelChapterInfo> updateNovelChapterInfo(
            NovelChapterInfoServiceInput.UpdateChapterInfoInput infoInput) {
        return chapterInfoCommonRepo.findById(infoInput.getChapterInfoId()).map(novelChapterInfo -> {
            BeanUtils.copyProperties(infoInput, novelChapterInfo);
            return ofSuccess(chapterInfoCommonRepo.save(novelChapterInfo));
        }).orElse(ofFail(message("no-such", "chapter info")));
    }

    @Override
    public BaseServiceOutput<NovelChapterInfo> findByChapterInfoId(String chapterInfoId) {
        return ofSuccess(chapterInfoCommonRepo.findById(chapterInfoId).orElse(null));
    }

    @Override
    public BaseServiceOutput<Page<NovelChapterInfo>> findChapterInfoPageByNovelId(String novelId, Pageable pageable) {
        if(!novelHeadCommonRepository.existsById(novelId))
            return ofFail(message("no-such","novel"));
        return ofSuccess(() -> chapterInfoCommonRepo.findAll(Specifications.equal("novelId", novelId), pageable));
    }
}
