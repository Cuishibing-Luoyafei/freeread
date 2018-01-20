package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.dao.WishItemDao;
import cui.shibing.freeread.model.WishItem;
import cui.shibing.freeread.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static cui.shibing.freeread.common.CommonUtils.emptyPageObject;
import static cui.shibing.freeread.common.CommonUtils.validatePageable;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishItemDao wishItemDao;

    @Override
    public Page<WishItem> getWishItemByNovelName(String novelName, Pageable pageable) {
        if (StringUtils.isEmpty(novelName) || !validatePageable(pageable)) {
            return emptyPageObject(pageable);
        }
        long count = wishItemDao.selectWishItemCountByNovelName(novelName);
        if (count > 0) {
            List<WishItem> result = wishItemDao.selectWishItemsByNovelName(novelName, pageable);
            return new PageImpl<WishItem>(result, pageable, count);
        }

        return emptyPageObject(pageable);

    }

    /**
     * 验证WishItem的有效性
     */
    private boolean validateWishItem(WishItem wishItem) {
        if (wishItem == null) {
            return false;
        }
        if (StringUtils.isEmpty(wishItem.getNovelName()) || StringUtils.isEmpty(wishItem.getUserName())
                || StringUtils.isEmpty(wishItem.getUserEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addWishItem(WishItem wishItem) {
        if (!validateWishItem(wishItem)) {
            return false;
        }
        return wishItemDao.insertWishItem(wishItem) == 1;
    }

    @Override
    public boolean removeWishItem(String userName, String userEmail, String novelName) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userEmail)
                || StringUtils.isEmpty(novelName)) {
            return false;
        }
        return wishItemDao.deleteWishItem(userName, userEmail, novelName) == 1;
    }

    @Override
    public Page<WishItem> getWishItemFromUser(String userName, Pageable pageable) {
        if (StringUtils.isEmpty(userName) || !validatePageable(pageable)) {
            return emptyPageObject(pageable);
        }

        long count = wishItemDao.selectCountOfUserWishItems(userName);
        if (count > 0) {
            List<WishItem> result = wishItemDao.selectWishItemsByUserName(userName, pageable);
            return new PageImpl<WishItem>(result, pageable, count);
        }

        return emptyPageObject(pageable);

    }

    @Override
    public Page<String> getNovelNamesFromWishItems(Pageable pageable) {
        if (!validatePageable(pageable)) {
            return emptyPageObject(pageable);
        }
        long count = wishItemDao.selectCountOfWishNovel();
        if (count > 0) {
            List<String> result = wishItemDao.selectWishNovelFromWishItems(pageable);
            return new PageImpl<String>(result, pageable, count);
        }
        return emptyPageObject(pageable);
    }

    @Override
    public void notifyUserWishItemNovel(List<WishItem> wishItems) {
        //TODO:通知用户WishItem中的小说已经被收录了
    }
}
