package cui.shibing.freeread.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cui.shibing.freeread.model.NovelContent;

public interface NovelContentService {
	/**
	 * 根据小说Id和章节数查找
	 * @param novelId 小说Id
	 * @param chapterIndex 章节数
	 * @return 返回相应的NovelContent
	 * */
	NovelContent searchByNovelHeadAndChapter(String novelId,Integer chapterIndex);
	
	/**
	 * 根据小说Id查找所有的章节
	 * @param novelId 小说Id
	 * @param pageable 分页对象
	 * @return 返回相应分页的NovelContent
	 * */
	Page<NovelContent> searchByNovelHeadId(String novelId,Pageable pageable);
	
	long searchNovelContentCountByNovelId(String novelId);
}
