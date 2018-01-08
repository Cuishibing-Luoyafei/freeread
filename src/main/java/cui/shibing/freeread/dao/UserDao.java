package cui.shibing.freeread.dao;

import cui.shibing.freeread.datasource.DataSource;
import cui.shibing.freeread.model.User;
import cui.shibing.freeread.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

public interface UserDao {

    /**
     * 插入一个User对象
     * */
    @DataSource
    int insertUser(User user);

    /**
     * 根据用户名删除一个用户对象
     * */
    @DataSource
    int deleteUserByName(String userName);

    /**
     * 根据用户名查找用户
     * */
    @DataSource(SLAVER)
    User selectByUserName(String userName);

    /**
     * 根据用户名更新用户
     * */
    @DataSource
    int updateUser(User user);

    /**
     * 根据用户名查找用户信息
     */
    @DataSource(SLAVER)
    UserInfo selectUserInfoByUserName(@Param("userName") String userName);

    /**
     * 插入用户信息
     */
    @DataSource
    int insertUserInfo(@Param("userInfo") UserInfo userInfo);

    /**
     * 更新用户信息
     */
    @DataSource
    int updateUserInfo(@Param("userInfo") UserInfo userInfo);

}
