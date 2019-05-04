package com.wooread.wooreadnovel.controller;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelChapterInfoServiceInput;
import com.wooread.wooreadnovel.model.NovelChapterInfo;
import com.wooread.wooreadnovel.service.NovelChapterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadbase.tools.MessageTools.message;

@RestController
public class NovelChapterInfoController {

    @Autowired
    private NovelChapterInfoService chapterInfoService;

    @PostMapping("updateNovelChapterInfo")
    public BaseServiceOutput<NovelChapterInfo> updateNovelChapterInfo(
            @RequestBody @Validated NovelChapterInfoServiceInput.UpdateChapterInfoInput infoInput,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ofFail(message(bindingResult.getFieldError()));
        return chapterInfoService.updateNovelChapterInfo(infoInput);
    }

    @GetMapping("findByChapterInfoId")
    public BaseServiceOutput<NovelChapterInfo> findByChapterInfoId(
            @RequestParam("chapterInfoId") String chapterInfoId) {
        return chapterInfoService.findByChapterInfoId(chapterInfoId);
    }

    @GetMapping("findChapterInfoPageByNovelId")
    public BaseServiceOutput<Page<NovelChapterInfo>> findChapterInfoPageByNovelId(
            @RequestParam("novelId") String novelId, Pageable pageable) {
        return chapterInfoService.findChapterInfoPageByNovelId(novelId, pageable);
    }

}
