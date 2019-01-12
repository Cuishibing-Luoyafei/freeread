package com.wooread.wooreadnovel.controller;

import com.wooread.wooreadnovel.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelChapterInfoServiceInput;
import com.wooread.wooreadnovel.model.NovelChapterInfo;
import com.wooread.wooreadnovel.service.NovelChapterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.wooread.wooreadnovel.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadnovel.tools.MessageTools.message;

@RestController
public class NovelChapterInfoController {

    @Autowired
    private NovelChapterInfoService chapterInfoService;

    @PostMapping("updateNovelChapterInfo")
    public BaseServiceOutput<NovelChapterInfo> updateNovelChapterInfo(
            @Validated NovelChapterInfoServiceInput.UpdateChapterInfoInput infoInput,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ofFail(message(bindingResult.getFieldError()));
        return chapterInfoService.updateNovelChapterInfo(infoInput);
    }

    @GetMapping("findByChapterInfoId")
    public BaseServiceOutput<NovelChapterInfo> findByChapterInfoId(
            @RequestParam("chapterInfoId") Integer chapterInfoId) {
        return chapterInfoService.findByChapterInfoId(chapterInfoId);
    }

    @GetMapping("findChapterInfoPageByNovelId")
    public BaseServiceOutput<Page<NovelChapterInfo>> findChapterInfoPageByNovelId(
            @RequestParam("novelId") Integer novelId, Pageable pageable) {
        return chapterInfoService.findChapterInfoPageByNovelId(novelId, pageable);
    }

}
