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
    
    //TODO:根据pageable的查询都需要查询一次记录的总数量
    long selectNovelHeadCountByNovelName(@Param("novelName") String novelName);
    
    long selectNovelHeadCountByPopularity();
}