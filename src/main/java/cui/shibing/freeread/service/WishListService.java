package cui.shibing.freeread.service;

import cui.shibing.freeread.model.WishItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 愿望单相关的Service
 */
public interface WishListService {

    /**
     * 查询关于某一本小说的WishItems
     * @param novelName 小说名称
     * @param pageable 分页对象
     * @return 对应的结果集
     * */
    List<WishItem> getWishItemByNovelName(String novelName, Pageable pageable);

    /**
     * 查询个人的WishItem
     *
     * @param userName 用户名称
     *
     * @return 对应的结果集
     */
    List<WishItem> getWishItemFromUser(String userName);

    /**
     * 查询在WishItem中的小说名称
     *
     * @param pageable 分页对象
     *
     * @return 对应的结果集
     */
    List<String> getNovelNamesFromWishItems(Pageable pageable);

    /**
     * 通知用户WishItem中的小说已经存在了
     *
     * @param wishItems 要通知的WishItems
     */
    void notifyUserWishItemNovel(List<WishItem> wishItems);

}
