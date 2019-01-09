package com.wooread.wooreadnovel.service;

import com.wooread.wooreadnovel.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelHeadServiceInput;
import com.wooread.wooreadnovel.model.NovelHead;

public interface NovelHeadService {
    BaseServiceOutput<NovelHead> createNovelHead(NovelHeadServiceInput.CreateNovelHeadInput input);

    BaseServiceOutput<NovelHead> updateNovelHead(NovelHeadServiceInput.UpdateNovelHeadInput input);
}
