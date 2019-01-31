package com.wooread.wooreadnovel.controller;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadbase.jwt.JwtUtils;
import com.wooread.wooreadnovel.dto.NovelChapterServiceInput;
import com.wooread.wooreadnovel.model.NovelChapter;
import com.wooread.wooreadnovel.service.NovelChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadbase.tools.MessageTools.message;

@RestController
public class NovelChapterController {

    @Autowired
    private NovelChapterService novelChapterService;

    @PostMapping("createNovelChapter")
    public BaseServiceOutput<NovelChapter> createNovelChapter(
            @RequestBody @Validated NovelChapterServiceInput.CreateNovelChapterInput input,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return ofFail(message(bindingResult.getFieldError()));

        return novelChapterService.createNovelChapter(input);
    }

    @PostMapping("updateNovelChapter")
    public BaseServiceOutput<NovelChapter> updateNovelChapter(
            @RequestBody @Validated NovelChapterServiceInput.UpdateNovelChapterInput input,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return ofFail(message(bindingResult.getFieldError()));
        return novelChapterService.updateNovelChapter(input);
    }

    @GetMapping("findNovelChapterByChapterId")
    public BaseServiceOutput<NovelChapter> findNovelChapterByChapterId(
            @RequestParam("novelChapterId") Integer novelChapterId, JwtUtils.DecodedToken token) {

        return novelChapterService.findNovelChapterByChapterId(novelChapterId);
    }

}
