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
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName(UUID.randomUUID().toString());
        user.setUserPass(UUID.randomUUID().toString());
        user.setUserRole(1);
        user.setUserInfoId(UUID.randomUUID().toString());
        assertTrue(userDao.insertUser(user)==1);
    }

    @Test
    public void testDeleteUser(){
        User user = new User();
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setUserName(UUID.randomUUID().toString());
        user.setUserPass(UUID.randomUUID().toString());
        user.setUserRole(1);
        user.setUserInfoId(UUID.randomUUID().toString());
        userDao.insertUser(user);
        assertTrue(userDao.selectByUserId(userId)!=null);
        assertTrue(userDao.deleteUserById(userId) == 1);
        assertTrue(userDao.selectByUserId(userId)==null);
    }
}
