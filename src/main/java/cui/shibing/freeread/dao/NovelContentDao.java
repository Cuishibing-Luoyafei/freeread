package cui.shibing.freeread.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import cui.shibing.freeread.model.NovelContent;

public interface NovelContentDao {
    
    int insertNovelContent(NovelContent record);
    
    NovelContent selectNovleContentByNovelIdAndChapter(@Param("novelId")String novelId,@Param("chapterIndex")Integer chapterIndex);
    
    List<NovelContent> selectNovelContentByNovelId(@Param("novelId")String novelId,@Param("pageable")Pageable pageable);
    
    long selectNovelContentCountByNovelId(@Param("novelId") String novelId);
    
}