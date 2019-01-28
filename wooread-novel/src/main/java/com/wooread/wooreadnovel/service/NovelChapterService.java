package com.wooread.wooreadnovel.service;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelChapterServiceInput;
import com.wooread.wooreadnovel.model.NovelChapter;
import com.wooread.wooreadnovel.model.NovelChapterInfo;

public interface NovelChapterService {

    BaseServiceOutput<NovelChapter> createNovelChapter(NovelChapterServiceInput.CreateNovelChapterInput input);

    BaseServiceOutput<NovelChapter> updateNovelChapter(NovelChapterServiceInput.UpdateNovelChapterInput input);

    BaseServiceOutput<NovelChapter> findNovelChapterByChapterId(Integer chapterId);

}
