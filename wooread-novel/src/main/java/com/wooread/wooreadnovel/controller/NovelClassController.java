package com.wooread.wooreadnovel.controller;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.model.NovelClass;
import com.wooread.wooreadnovel.service.NovelClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NovelClassController {

    @Autowired
    private NovelClassService novelClassService;

    @GetMapping("novelClasses")
    public BaseServiceOutput<List<NovelClass>> allNovelClass() {
        return BaseServiceOutput.ofSuccess(()->{
            return novelClassService.findAll();
        });
    }

    @PutMapping("novelClass")
    public BaseServiceOutput<Boolean> addNovelClass(@RequestBody NovelClass novelClass) {
        return novelClassService.add(novelClass);
    }

    @DeleteMapping("novelClass/{id}")
    public BaseServiceOutput<Boolean> removeNovelClass(@PathVariable("id") Integer classId) {
        return novelClassService.remove(classId);
    }
}
