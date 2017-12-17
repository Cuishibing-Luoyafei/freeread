package cui.shibing.freeread.service;

import cui.shibing.freeread.dto.NovelChapterInfoDto;
import cui.shibing.freeread.model.NovelChapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

	/**
	 * 查询一个小说的章节总数
	 * @param novelId 小说id
	 * @return 章节数目
	 * */
	long searchNovelChapterCountByNovelId(String novelId);
	
	/**
	 * 查询章节信息,不包含章节内容
	 * @param novelId 小说id
	 * @param pageable 分页对象
	 * @return 相应分页的章节信息
	 * */
	Page<NovelChapterInfoDto> searchChapterInfoByNovelId(String novelId,Pageable pageable);

	/**
	 * 增加一个小说章节
	 * @param novelChapter 要增加的小说章节
	 * @return 是否成功
	 * */
	boolean addNovelChapter(NovelChapter novelChapter);

	/**
	 * 根据小说id删除全部的小说章节
	 */
	boolean removeNovelChapter(String novelId);
}
