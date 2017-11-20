package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.dao.UserDao;
import cui.shibing.freeread.model.User;
import cui.shibing.freeread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(String userId) {
        if(!StringUtils.isEmpty(userId)){
            return userDao.selectByUserId(userId);
        }
        return null;
    }

    @Override
    public User getUserByName(String userName) {
        return null;
    }

    @Override
    public int deleteUserById(String userId) {
        return 0;
    }

    @Override
    public int updateUserById(User user) {
        return 0;
    }
}
