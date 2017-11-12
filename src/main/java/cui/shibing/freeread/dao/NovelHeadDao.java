package cui.shibing.freeread.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import cui.shibing.freeread.model.NovelHead;

public interface NovelHeadDao {
    int deleteNovelHeadByNovelId(String novelId);

    int insertNovelHead(NovelHead record);

    NovelHead selectNovelHeadByNovelId(String novelId);

    int updateNovelHeadByNovelId(NovelHead record);
    
    List<NovelHead> selectNovelHeadByNovelName(@Param("novelName")String novelName,@Param("pageable")Pageable pageable); 
    
    List<NovelHead> selectNovelHeadByNovelClassName(@Param("novelClassName")String novelClassName,@Param("pageable")Pageable pageable);
    
    List<NovelHead> selectNovelHeadByPopularity(@Param("pageable")Pageable pageable);
    
    long selectNovelHeadCountByNovelName(@Param("novelName") String novelName);
    long selectNovelHeadCountByNovelClass(@Param("novelClassName")String novelCassName);
    long selectNovelHeadCountByPopularity();
}