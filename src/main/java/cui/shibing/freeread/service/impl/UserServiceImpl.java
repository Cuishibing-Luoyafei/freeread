package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.dao.UserDao;
import cui.shibing.freeread.model.User;
import cui.shibing.freeread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String userName) {
        if(!StringUtils.isEmpty(userName)){
            return userDao.selectByUserName(userName);
        }
        return null;
    }

    @Override
    public int deleteUserByName(String userName) {
        if(!StringUtils.isEmpty(userName)){
            return userDao.deleteUserByName(userName);
        }
        return 0;
    }

    @Override
    public int updateUserByName(User user) {
        if(user != null && !StringUtils.isEmpty(user.getUserName())){
            return userDao.updateUser(user);
        }
        return 0;
    }

    @Override
    public boolean registerUser(String userName, String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return false;
        }
        User existUser = userDao.selectByUserName(userName);
        if (existUser != null) {
            return false;
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserPass(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setUserRole(UserService.DEFAULT_ROLE);
        return userDao.insertUser(user) == 1;
    }
}
