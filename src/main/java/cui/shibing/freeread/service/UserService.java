package cui.shibing.freeread.service;

import cui.shibing.freeread.model.User;

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
     * 普通用户权限
     */
    int DEFAULT_ROLE = 1 << 1;

    /**
     * 管理员用户权限
     */
    int ADMIN_ROLE = 1<<2;
}
