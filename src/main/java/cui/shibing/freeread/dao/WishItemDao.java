package cui.shibing.freeread.dao;

import cui.shibing.freeread.datasource.DataSource;
import cui.shibing.freeread.datasource.DataSourceType;
import cui.shibing.freeread.model.WishItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

public interface WishItemDao {

    /**
     * 保存一个WishItem
     */
    @DataSource
    int insertWishItem(@Param("wishItem") WishItem wishItem);

    /**
     * 删除一个WishItem(根据主键)
     */
    @DataSource(SLAVER)
    int deleteWishItem(@Param("userName") String userName, @Param("userEmail") String userEmail, @Param("novelName") String novelName);

    /**
     * 更新一个WishItem(主键不能更新,其它的是按需更新,如果提供了就更新,如果没有提供就不更新)
     */
    @DataSource
    int updateWishItem(@Param("wishItem") WishItem wishItem);

    /**
     * 查询用户想看的小说有哪些
     */
    @DataSource(SLAVER)
    List<String> selectWishNovelFromWishItems(@Param("pageable") Pageable pageable);

    /**
     * 用户想看的小说的数量
     */
    @DataSource(SLAVER)
    long selectCountOfWishNovel();

    /**
     * 根据用户名查询WishItem
     */
    @DataSource(SLAVER)
    List<WishItem> selectWishItemsByUserName(@Param("userName") String userName, @Param("pageable") Pageable pageable);

    /**
     * 根据用户名查询WishItem的数量
     */
    @DataSource(SLAVER)
    long selectCountOfUserWishItems(@Param("userName") String userName);

    /**
     * 根据给定的小说名查询相关的WishItem(模糊查询)
     */
    @DataSource(SLAVER)
    List<WishItem> selectWishItemsByNovelName(@Param("novelName") String novelName, @Param("pageable") Pageable pageable);

    /**
     * 根据给定的小说名查询相关的WishItem的数量
     */
    @DataSource(SLAVER)
    long selectWishItemCountByNovelName(@Param("novelName") String novelName);

}
