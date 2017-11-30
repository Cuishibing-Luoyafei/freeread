package cui.shibing.freeread.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import cui.shibing.freeread.model.NovelChapter;

public interface NovelChapterDao {
    
    int insertNovelChapter(NovelChapter record);
    
    NovelChapter selectNovelChapterByNovelIdAndChapterIndex(@Param("novelId")String novelId, @Param("chapterIndex")Integer chapterIndex);
    
    List<NovelChapter> selectNovelChapterByNovelId(@Param("novelId")String novelId,@Param("pageable")Pageable pageable);
    
    List<NovelChapter> selectNovelChapterInfoByNovelId(@Param("novelId")String novelId,@Param("pageable")Pageable pageable);
    
    long selectNovelChapterCountByNovelId(@Param("novelId") String novelId);
    
    
}