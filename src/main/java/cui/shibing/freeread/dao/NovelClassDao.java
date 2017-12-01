package cui.shibing.freeread.dao;

import java.util.List;

import cui.shibing.freeread.model.NovelClass;

public interface NovelClassDao {

	/**
	 * 查询所有的小说类别
	 * */
	List<NovelClass> selectAllClass();
}
