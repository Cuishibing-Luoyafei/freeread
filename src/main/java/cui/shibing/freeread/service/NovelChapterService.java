package cui.shibing.freeread.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cui.shibing.freeread.dto.NovelChapterInfoDto;
import cui.shibing.freeread.model.NovelChapter;

public interface NovelChapterService {
	/**
	 * 根据小说Id和章节数查找
	 * @param novelId 小说Id
	 * @param chapterIndex 章节数
	 * @return 返回相应的NovelChapter
	 * */
	NovelChapter searchByNovelHeadAndChapter(String novelId,Integer chapterIndex);
	
	/**
	 * 根据小说Id查找所有的章节
	 * @param novelId 小说Id
	 * @param pageable 分页对象
	 * @return 返回相应分页的NovelChapter
	 * */
	Page<NovelChapter> searchByNovelHeadId(String novelId,Pageable pageable);
	
	long searchNovelChapterCountByNovelId(String novelId);
	
	/**
	 * 查询章节信息,不包含章节内容
	 * @param novelId 小说id
	 * @param pageable 分页对象
	 * @return 相应分页的章节信息
	 * */
	Page<NovelChapterInfoDto> searchChapterInfoByNovelId(String novelId,Pageable pageable);
}
