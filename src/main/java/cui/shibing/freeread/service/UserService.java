package cui.shibing.freeread.service;

import cui.shibing.freeread.model.User;

public interface UserService {
    /**
     * @deprecated 根据用户名获取用户实体
     * @param userName 用户名
     * @return 用户名对应的用户
     * */
    User getUserByName(String userName);

    /**
     * @deprecated 根据用户名删除用户
     * @param userName 要删除的用户名
     * @return 删除的记录数
     * */
    int deleteUserByName(String userName);

    /**
     * @deprecated 根据用户名更新用户
     * @param user 要更新的用户
     * @return 更新记录的数量
     * */
    int updateUserByName(User user);

    boolean registerUser(String userName, String password);
}
