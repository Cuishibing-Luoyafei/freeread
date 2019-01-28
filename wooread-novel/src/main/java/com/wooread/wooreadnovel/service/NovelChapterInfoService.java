package com.wooread.wooreadnovel.service;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelChapterInfoServiceInput;
import com.wooread.wooreadnovel.model.NovelChapterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NovelChapterInfoService {

    BaseServiceOutput<NovelChapterInfo> updateNovelChapterInfo(NovelChapterInfoServiceInput.UpdateChapterInfoInput infoInput);

    BaseServiceOutput<NovelChapterInfo> findByChapterInfoId(Integer chapterInfoId);

    BaseServiceOutput<Page<NovelChapterInfo>> findChapterInfoPageByNovelId(Integer novelId, Pageable pageable);

}
