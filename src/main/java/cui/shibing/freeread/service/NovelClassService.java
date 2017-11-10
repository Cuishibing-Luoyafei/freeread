package cui.shibing.freeread.service;

import java.util.List;

import cui.shibing.freeread.model.NovelClass;

public interface NovelClassService {
	/**
	 * 获取所有的小说类别
	 * @return 当前所有的小说类别
	 * */
	List<NovelClass> getallNovelClasses();
}
