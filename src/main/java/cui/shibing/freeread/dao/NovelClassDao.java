package cui.shibing.freeread.dao;

import java.util.List;

import cui.shibing.freeread.datasource.DataSourceTypeSetter;
import cui.shibing.freeread.model.NovelClass;

import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

public interface NovelClassDao {

	/**
	 * 查询所有的小说类别
	 * */
	@DataSourceTypeSetter(SLAVER)
	List<NovelClass> selectAllClass();
}
