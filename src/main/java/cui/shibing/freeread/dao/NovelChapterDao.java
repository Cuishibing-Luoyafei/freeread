package cui.shibing.freeread.dao;

import cui.shibing.freeread.datasource.DataSourceTypeSetter;
import cui.shibing.freeread.model.NovelChapter;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

public interface NovelChapterDao {

    /**
     * 插入一个小说章节
     * @param record 要插入的章节对象
     * @return 插入成功的数目
     * */
    @DataSourceTypeSetter
    int insertNovelChapter(NovelChapter record);

    /**
     * 根据小说id和章节数查询小说章节
     * @param novelId 小说id
     * @param chapterIndex 章节数
     * @return 相应的小说章节对象
     * */
    @DataSourceTypeSetter(SLAVER)
    NovelChapter selectNovelChapterByNovelIdAndChapterIndex(@Param("novelId")String novelId, @Param("chapterIndex")Integer chapterIndex);

    /**
     * 查询一个小说的所有章节,包含小说的内容
     * @param novelId 小说id
     * @param pageable 分页对象
     * @return 相应的章节记录
     * */
    @DataSourceTypeSetter(SLAVER)
    List<NovelChapter> selectNovelChapterByNovelId(@Param("novelId")String novelId,@Param("pageable")Pageable pageable);

    /**
     * 查询一个小说的所有章节,不包含小说的内容
     * @param novelId 小说id
     * @param pageable 分页对象
     * @return 相应的章节记录
     * */
    @DataSourceTypeSetter(SLAVER)
    List<NovelChapter> selectNovelChapterInfoByNovelId(@Param("novelId")String novelId,@Param("pageable")Pageable pageable);

    /**
     * 查询一个小说的章节数目
     * @param novelId 小说id
     * @return 小说的章节数目
     * */
    @DataSourceTypeSetter(SLAVER)
    long selectNovelChapterCountByNovelId(@Param("novelId") String novelId);
}