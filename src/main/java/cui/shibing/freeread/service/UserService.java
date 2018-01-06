package cui.shibing.freeread.service;

import cui.shibing.freeread.model.User;
import cui.shibing.freeread.model.UserInfo;

public interface UserService {
    /**
     * 根据用户名获取用户实体
     * @param userName 用户名
     * @return 用户名对应的用户
     * */
    User getUserByName(String userName);

    /**
     * 根据用户名删除用户
     * @param userName 要删除的用户名
     * @return 删除的记录数
     * */
    int deleteUserByName(String userName);

    /**
     * 根据用户名更新用户
     * @param user 要更新的用户
     * @return 更新记录的数量
     * */
    int updateUserByName(User user);

    /**
     * 注册用户，权限默认为普通用户权限权限
     *
     * @param userName 用户名
     * @param password 用户密码
     */
    boolean registerUser(String userName, String password);

    /**
     * 获取用户的个人信息
     *
     * @param userName 用户名
     *
     * @return 用户个人信息
     */
    UserInfo getUserInfo(String userName);

    /**
     * 更新用户个人信息
     *
     * @param userName 用户名
     * @param userInfo 用户信息
     *
     * @return 是否成功
     */
    boolean updateUserInfo(String userName, UserInfo userInfo);

    /**
     * 更新用户邮箱
     * @param userName 用户名
     * @param email 用户邮箱
     * @return 是否成功
     * */
    boolean updateUserEmail(String userName, String email);

    /**
     * 插入用户个人信息
     *
     * @param userInfo 用户信息
     *
     * @return 是否成功
     */
    boolean insertUserInfo(UserInfo userInfo);

    /**
     * 普通用户权限
     */
    int DEFAULT_ROLE = 1 << 1;

    /**
     * 管理员用户权限
     */
    int ADMIN_ROLE = 1 << 2;
}
