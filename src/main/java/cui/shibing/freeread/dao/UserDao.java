package cui.shibing.freeread.dao;

import cui.shibing.freeread.model.User;

public interface UserDao {

    /**
     * 插入一个User对象
     * */
    int insertUser(User user);

    /**
     * 根据用户名删除一个用户对象
     * */
    int deleteUserByName(String userName);

    /**
     * 根据用户名查找用户
     * */
    User selectByUserName(String userName);

    /**
     * 根据用户名更新用户
     * */
    int updateUser(User user);
}
