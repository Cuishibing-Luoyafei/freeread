package com.wooread.wooreadnovel.controller;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelHeadServiceInput;
import com.wooread.wooreadnovel.model.NovelHead;
import com.wooread.wooreadnovel.service.NovelHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadbase.tools.MessageTools.message;

@RestController
public class NovelHeadController {

    @Autowired
    private NovelHeadService novelHeadService;

    @PostMapping("createNovelHead")
    public BaseServiceOutput<NovelHead> createNovelHead(@RequestBody @Validated NovelHeadServiceInput.CreateNovelHeadInput input,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ofFail(message(bindingResult.getFieldError()));
        }
        return novelHeadService.createNovelHead(input);
    }

    @PostMapping("updateNovelHead")
    public BaseServiceOutput<NovelHead> updateNovelHead(@RequestBody @Validated NovelHeadServiceInput.UpdateNovelHeadInput input,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ofFail(message(bindingResult.getFieldError()));
        }
        return novelHeadService.updateNovelHead(input);
    }

    @GetMapping("findByLikeName")
    public BaseServiceOutput<Page<NovelHead>> findByLikeName(@RequestParam("name") String name, Pageable pageable){
        String a = "";
        a = a + null;
        return novelHeadService.findByLikeName(name,pageable);
    }

}
