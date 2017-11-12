package cui.shibing.freeread.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cui.shibing.freeread.model.NovelHead;
public interface NovelHeadService {
	/**
	 * 根据小说名称查找小说
	 * @param novelName 要查找的小说名
	 * @return 返回 novelName 对应的小说
	 * */
	Page<NovelHead> searchByNovelName(String novelName,Pageable pageable);
	
	/**
	 * 查询小说名称对应记录的数量
	 * @param novelName 小说名称
	 * @return 对应的数量
	 * */
	long searchCountByNovelName(String novelName);
	
	/**
	 * 根据小说类别查找小说
	 * @param className 小说类别名
	 * @param pageable 分页对象
	 * @return 返回 className 对应的小说
	 * */
	Page<NovelHead> searchByNovelClass(String className,Pageable pageable);
	
	/**
	 * 根据小说的热度查找小说
	 * @param pageable 分页对象
	 * @return 返回相应的小说
	 * */
	Page<NovelHead> searchByPopularity(Pageable pageable);
	
	/**
	 * 查询小说总数
	 * @return 小说数量
	 * */
	long searchNovelHeadCount();
	
	/**
	 * 查询某一类别小说的总数
	 * @param className 类别名称
	 * @return 该类别小说的数量
	 * */
	long searchCountByClassName(String className);
	
	/**
	 * 根据小说Id查找小说
	 * @param nonelId 小说Id
	 * @return 返回相应的小说
	 * */
	NovelHead searchByNovelId(String novelId);
}
