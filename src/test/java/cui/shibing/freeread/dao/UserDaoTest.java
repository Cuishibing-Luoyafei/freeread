package cui.shibing.freeread.dao;

import cui.shibing.freeread.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static junit.framework.Assert.*;

public class UserDaoTest extends CustomDaoTest{
    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUserName(UUID.randomUUID().toString());
        user.setUserPass(UUID.randomUUID().toString());
        user.setUserRole(1);
        user.setUserInfoId(UUID.randomUUID().toString());
        assertTrue(userDao.insertUser(user)==1);
    }

    @Test
    public void testDeleteUser(){
        User user = new User();
        String userName = UUID.randomUUID().toString();
        user.setUserName(userName);
        user.setUserPass(UUID.randomUUID().toString());
        user.setUserRole(1);
        user.setUserInfoId(UUID.randomUUID().toString());
        userDao.insertUser(user);
        assertTrue(userDao.selectByUserName(userName)!=null);
        assertTrue(userDao.deleteUserByName(userName) == 1);
        assertTrue(userDao.selectByUserName(userName)==null);
    }
}
