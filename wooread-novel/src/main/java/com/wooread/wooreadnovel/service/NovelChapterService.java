package com.wooread.wooreadnovel.service;

import com.wooread.wooreadnovel.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelChapterServiceInput;
import com.wooread.wooreadnovel.model.NovelChapter;

public interface NovelChapterService {

    BaseServiceOutput<NovelChapter> createNovelChapter(NovelChapterServiceInput.CreateNovelChapterInput input);

    BaseServiceOutput<NovelChapter> updateNovelChapter(NovelChapterServiceInput.UpdateNovelChapterInput input);

    BaseServiceOutput<NovelChapter> findNovelChapterByChapterId(Integer chapterId);

}
