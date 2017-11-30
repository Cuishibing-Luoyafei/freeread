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

    @Test
    public void testSelectByUserName(){
        User user = new User();
        String userName = UUID.randomUUID().toString();
        user.setUserName(userName);
        user.setUserPass(UUID.randomUUID().toString());
        user.setUserRole(1);
        user.setUserInfoId(UUID.randomUUID().toString());
        userDao.insertUser(user);

        User selectedUser = userDao.selectByUserName(userName);
        assertTrue(selectedUser!=null);
        assertTrue(selectedUser.getUserName().equals(userName));
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        String userName = UUID.randomUUID().toString();
        user.setUserName(userName);
        user.setUserPass(UUID.randomUUID().toString());
        user.setUserRole(1);
        user.setUserInfoId(UUID.randomUUID().toString());
        userDao.insertUser(user);
        String updatedPass = "testPass";
        user.setUserPass(updatedPass);
        assertTrue(userDao.updateUser(user) == 1);
        User selectedUser = userDao.selectByUserName(userName);
        assertTrue(selectedUser.getUserPass().equals(updatedPass));
    }
}
