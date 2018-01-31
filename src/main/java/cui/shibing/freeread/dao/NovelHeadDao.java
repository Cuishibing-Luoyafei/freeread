package cui.shibing.freeread.dao;

import cui.shibing.freeread.datasource.DataSource;
import cui.shibing.freeread.datastrategy.SelectNovelHeadByIdStrategy;
import cui.shibing.freeread.model.NovelHead;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

public interface NovelHeadDao {
    /**
     * 根据小说id删除一个NovelHead
     * */
    @DataSource
    int deleteNovelHeadByNovelId(String novelId);

    /**
     * 插入一个NovelHead
     * */
    @DataSource
    int insertNovelHead(NovelHead record);

    /**
     * 根据小说id查询NovelHead
     * */
    @DataSource(value = SLAVER, dataSourceName = "default-database", originalTableName = "novel_head",
            dataInfo = SelectNovelHeadByIdStrategy.class)
    NovelHead selectNovelHeadByNovelId(String novelId);

    /**
     * 根据小说的id更新NovelHead
     * */
    @DataSource
    int updateNovelHeadByNovelId(NovelHead record);

    /**
     * 根据小说名查询小说
     * @param novelName 小说名称
     * @param pageable 分页对象
     * @return 相应的小说记录
     * */
    @DataSource(SLAVER)
    List<NovelHead> selectNovelHeadByNovelName(@Param("novelName")String novelName,
                                               @Param("pageable")Pageable pageable);

    /**
     * 根据小说类名查找小说
     * @param novelClassName 小说类名
     * @param pageable 分页对象
     * @return 相应的小说记录
     * */
    @DataSource(SLAVER)
    List<NovelHead> selectNovelHeadByNovelClassName(@Param("novelClassName")String novelClassName,
                                                    @Param("pageable")Pageable pageable);

    /**
     * 根据小说的点击量查找小说
     * @param pageable 分页对象
     * @return 相应的小说记录
     * */
    @DataSource(SLAVER)
    List<NovelHead> selectNovelHeadByPopularity(@Param("pageable")Pageable pageable);

    /**
     * 根据小说的作者查找小说
     * @param userName 作者名
     * @param pageable 分页对象
     * @return 相应的小说记录
     * */
    @DataSource(SLAVER)
    List<NovelHead> selectNovelHeadByAuthor(@Param("userName")String userName,
                                            @Param("pageable")Pageable pageable);

    /**
     * 根据小说名查找小说的数量
     * */
    @DataSource(SLAVER)
    long selectNovelHeadCountByNovelName(@Param("novelName") String novelName);

    /**
     * 根据小说类别查找小说的数量
     * */
    @DataSource(SLAVER)
    long selectNovelHeadCountByNovelClass(@Param("novelClassName")String novelCassName);

    /**
     * 根据小说的点击量查找小说的数量
     * */
    @DataSource(SLAVER)
    long selectNovelHeadCountByPopularity();

    /**
     * 根据小说的作者查找小说的数量
     * */
    @DataSource(SLAVER)
    long selectNovelHeadCountByAuthor(@Param("userName")String userName);
}