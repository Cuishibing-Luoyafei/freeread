package cui.shibing.freeread.dao;

import cui.shibing.freeread.datasource.DataSourceTypeSetter;
import cui.shibing.freeread.model.User;

import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

public interface UserDao {

    /**
     * 插入一个User对象
     * */
    @DataSourceTypeSetter
    int insertUser(User user);

    /**
     * 根据用户名删除一个用户对象
     * */
    @DataSourceTypeSetter
    int deleteUserByName(String userName);

    /**
     * 根据用户名查找用户
     * */
    @DataSourceTypeSetter(SLAVER)
    User selectByUserName(String userName);

    /**
     * 根据用户名更新用户
     * */
    @DataSourceTypeSetter
    int updateUser(User user);
}
