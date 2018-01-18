package cui.shibing.freeread.service;

import cui.shibing.freeread.model.WishItem;
import org.springframework.data.domain.Page;
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
    Page<WishItem> getWishItemByNovelName(String novelName, Pageable pageable);

    /**
     * 添加一个WisItem
     *
     * @param wishItem 要添加的WishItem
     * @return 是否添加成功
     */
    boolean addWishItem(WishItem wishItem);

    /**
     * 删除一个WishItem
     *
     * @param novelName 小说名
     * @param userEmail 用户邮箱
     * @param userName  用户名
     * @return 是否删除成功
     */
    boolean removeWishItem(String userName, String userEmail, String novelName);

    /**
     * 查询个人的WishItem
     *
     * @param userName 用户名称
     * @param pageable 分页对象
     *
     * @return 对应的结果集
     */
    Page<WishItem> getWishItemFromUser(String userName, Pageable pageable);

    /**
     * 查询在WishItem中的小说名称
     *
     * @param pageable 分页对象
     *
     * @return 对应的结果集
     */
    Page<String> getNovelNamesFromWishItems(Pageable pageable);

    /**
     * 通知用户WishItem中的小说已经存在了
     *
     * @param wishItems 要通知的WishItems
     */
    void notifyUserWishItemNovel(List<WishItem> wishItems);

}
