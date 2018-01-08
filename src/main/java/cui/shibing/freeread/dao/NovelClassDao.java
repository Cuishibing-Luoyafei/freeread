package cui.shibing.freeread.dao;

import cui.shibing.freeread.datasource.DataSource;
import cui.shibing.freeread.model.NovelClass;

import java.util.List;

import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

public interface NovelClassDao {

	/**
	 * 查询所有的小说类别
	 * */
	@DataSource(SLAVER)
	List<NovelClass> selectAllClass();
}
