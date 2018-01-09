package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.dao.SubscribNovelDao;
import cui.shibing.freeread.model.SubscribNovel;
import cui.shibing.freeread.model.UserInfo;
import cui.shibing.freeread.service.SubscribNovelService;
import cui.shibing.freeread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class SubscribNovelServiceImpl implements SubscribNovelService {

    @Autowired
    private SubscribNovelDao subscribNovelDao;

    @Autowired
    private UserService userService;

    @Override
    public boolean addSubscribNovel(String novelName, String userName) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(novelName)) {
            return false;
        }
        UserInfo userInfo = userService.getUserInfo(userName);
        /**
         * 还没有设定邮箱
         * */
        if (userInfo == null || StringUtils.isEmpty(userInfo.getUserEmail())) {
            return false;
        }
        SubscribNovel subscribNovel = new SubscribNovel();
        subscribNovel.setEmail(userInfo.getUserEmail());
        subscribNovel.setNovelName(novelName.trim());
        subscribNovel.setSended(false);
        return subscribNovelDao.insertSubscribNovel(subscribNovel) == 1;
    }

    @Override
    public boolean removeSendedSubscribNovel() {
        return subscribNovelDao.deleteSendedSubscribNovel() > 0;
    }

    @Override
    public List<SubscribNovel> getSubscribNovel(long length, boolean isSended) {
        if (length < 1) {
            return Collections.emptyList();
        }
        return subscribNovelDao.selectSubscribNovel(length, isSended);
    }

    @Override
    public boolean updateSubscribNovel(SubscribNovel subscribNovel) {
        if (subscribNovel == null) {
            return false;
        }
        if (StringUtils.isEmpty(subscribNovel.getEmail()) || StringUtils.isEmpty(subscribNovel.getNovelName())) {
            return false;
        }
        return subscribNovelDao.updateSubscribNoveStatus(subscribNovel) == 1;
    }
}
