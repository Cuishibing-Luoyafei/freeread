package cui.shibing.freeread.dao;

import cui.shibing.freeread.model.User;

public interface UserDao {
    int insertUser(User user);
    int deleteUserByName(String userName);
    User selectByUserName(String userName);
    int updateUser(User user);
}
