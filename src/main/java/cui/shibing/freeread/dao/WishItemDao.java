package cui.shibing.freeread.dao;

import cui.shibing.freeread.model.WishItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WishItemDao {

    /**
     * 保存一个WishItem
     */
    int insertWishItem(@Param("wishItem") WishItem wishItem);

    /**
     * 删除一个WishItem(根据主键)
     */
    int deleteWishItem(@Param("userName") String userName, @Param("userEmail") String userEmail, @Param("novelName") String novelName);

    /**
     * 更新一个WishItem(主键不能更新,其它的是按需更新,如果提供了就更新,如果没有提供就不更新)
     */
    int updateWishItem(@Param("wishItem") WishItem wishItem);

    /**
     * 查询用户想看的小说有哪些
     */
    List<String> selectWishNovelFromWishItems(@Param("pageable") Pageable pageable);

    /**
     * 用户想看的小说的数量
     */
    long selectCountOfWishNovel();

    /**
     * 根据用户名查询WishItem
     */
    List<WishItem> selectWishItemsByUserName(@Param("userName") String userName, @Param("pageable") Pageable pageable);

    /**
     * 根据用户名查询WishItem的数量
     */
    long selectCountOfUserWishItems(@Param("userName") String userName);

    /**
     * 根据给定的小说名查询相关的WishItem(模糊查询)
     */
    List<WishItem> selectWishItemsByNovelName(@Param("novelName") String novelName, @Param("pageable") Pageable pageable);

    /**
     * 根据给定的小说名查询相关的WishItem的数量
     */
    long selectWishItemCountByNovelName(@Param("novelName") String novelName);

}
