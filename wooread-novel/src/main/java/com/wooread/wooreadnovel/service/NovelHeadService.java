package com.wooread.wooreadnovel.service;

import com.wooread.wooreadnovel.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelHeadServiceInput;
import com.wooread.wooreadnovel.model.NovelHead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NovelHeadService {
    BaseServiceOutput<NovelHead> createNovelHead(NovelHeadServiceInput.CreateNovelHeadInput input);

    BaseServiceOutput<NovelHead> updateNovelHead(NovelHeadServiceInput.UpdateNovelHeadInput input);

    BaseServiceOutput<Boolean> deleteNovelHead(Integer novelId);

    BaseServiceOutput<Page<NovelHead>> findByLikeName(String name, Pageable pageable);
}
