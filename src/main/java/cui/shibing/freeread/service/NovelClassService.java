package cui.shibing.freeread.service;

import cui.shibing.freeread.model.NovelClass;

import java.util.List;

public interface NovelClassService {
	
	/**
	 * 获取所有的小说类别
	 * 
	 * @return 当前所有的小说类别
	 */
	List<NovelClass> getAllNovelClasses();
}
