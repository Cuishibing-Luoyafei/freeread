package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.dao.WishItemDao;
import cui.shibing.freeread.model.WishItem;
import cui.shibing.freeread.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

import static cui.shibing.freeread.tools.CommonUtils.validatePageable;

public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishItemDao wishItemDao;

    @Override
    public List<WishItem> getWishItemByNovelName(String novelName, Pageable pageable) {
        if (StringUtils.isEmpty(novelName) || !validatePageable(pageable)) {
            return Collections.emptyList();
        }
        return wishItemDao.selectWishItemsByNovelName(novelName);
    }

    @Override
    public List<WishItem> getWishItemFromUser(String userName) {
        return null;
    }

    @Override
    public List<String> getNovelNamesFromWishItems(Pageable pageable) {
        return null;
    }

    @Override
    public void notifyUserWishItemNovel(List<WishItem> wishItems) {

    }
}
