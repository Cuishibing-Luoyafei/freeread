package com.wooread.wooreadnovel.service;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelHeadServiceInput;
import com.wooread.wooreadnovel.model.NovelHead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NovelHeadService {
    BaseServiceOutput<NovelHead> createNovelHead(NovelHeadServiceInput.CreateNovelHeadInput input);

    BaseServiceOutput<NovelHead> updateNovelHead(NovelHeadServiceInput.UpdateNovelHeadInput input);

    BaseServiceOutput<Boolean> deleteNovelHead(String novelId);

    BaseServiceOutput<Page<NovelHead>> listNovelHeads(NovelHeadServiceInput.QueryNovelHeadInput input,Pageable pageable);
}
