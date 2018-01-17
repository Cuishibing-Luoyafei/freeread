package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.dao.WishItemDao;
import cui.shibing.freeread.model.WishItem;
import cui.shibing.freeread.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static cui.shibing.freeread.common.CommonUtils.emptyPage;
import static cui.shibing.freeread.common.CommonUtils.validatePageable;

public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishItemDao wishItemDao;

    @Override
    public Page<WishItem> getWishItemByNovelName(String novelName, Pageable pageable) {
        if (StringUtils.isEmpty(novelName) || !validatePageable(pageable)) {
            return emptyPage(pageable);
        }
        long count = wishItemDao.selectWishItemCountByNovelName(novelName);
        if (count > 0) {
            List<WishItem> result = wishItemDao.selectWishItemsByNovelName(novelName, pageable);
            return new PageImpl<WishItem>(result, pageable, count);
        }

        return emptyPage(pageable);

    }

    @Override
    public Page<WishItem> getWishItemFromUser(String userName, Pageable pageable) {
        if (StringUtils.isEmpty(userName) || !validatePageable(pageable)) {
            return emptyPage(pageable);
        }

        long count = wishItemDao.selectCountOfUserWishItems(userName);
        if (count > 0) {
            List<WishItem> result = wishItemDao.selectWishItemsByUserName(userName, pageable);
            return new PageImpl<WishItem>(result, pageable, count);
        }

        return emptyPage(pageable);

    }

    @Override
    public Page<String> getNovelNamesFromWishItems(Pageable pageable) {
        if (!validatePageable(pageable)) {
            return emptyPage(pageable);
        }
        long count = wishItemDao.selectCountOfWishNovel();
        if (count > 0) {
            List<String> result = wishItemDao.selectWishNovelFromWishItems(pageable);
            return new PageImpl<String>(result, pageable, count);
        }
        return emptyPage(pageable);
    }

    @Override
    public void notifyUserWishItemNovel(List<WishItem> wishItems) {

    }
}
