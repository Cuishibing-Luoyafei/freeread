package com.wooread.wooreadnovel.controller;

import com.netflix.discovery.converters.Auto;
import com.wooread.wooreadnovel.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelHeadServiceInput;
import com.wooread.wooreadnovel.model.NovelHead;
import com.wooread.wooreadnovel.service.NovelHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wooread.wooreadnovel.dto.BaseServiceOutput.CODE_FAIL;
import static com.wooread.wooreadnovel.tools.MessageTools.message;

@RestController
public class NovelHeadController {

    @Autowired
    private NovelHeadService novelHeadService;

    @PostMapping("createNovelHead")
    public BaseServiceOutput<NovelHead> createNovelHead(@Validated NovelHeadServiceInput.CreateNovelHeadInput input,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new BaseServiceOutput<>(CODE_FAIL, message(bindingResult.getFieldError()));
        }
        return novelHeadService.createNovelHead(input);
    }

    @PostMapping("updateNovelHead")
    public BaseServiceOutput<NovelHead> updateNovelHead(@Validated NovelHeadServiceInput.UpdateNovelHeadInput input,
                                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new BaseServiceOutput<>(CODE_FAIL, message(bindingResult.getFieldError()));
        }
        return novelHeadService.updateNovelHead(input);
    }

}
