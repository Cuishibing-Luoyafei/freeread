package com.wooread.wooreadnovel.service;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.model.NovelClass;

import java.util.List;

public interface NovelClassService {
    List<NovelClass> findAll();

    BaseServiceOutput<Boolean> add(NovelClass novelClass);

    BaseServiceOutput<Boolean> remove(Integer classId);
}
