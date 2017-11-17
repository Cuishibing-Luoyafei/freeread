package cui.shibing.freeread.dao;

import cui.shibing.freeread.model.User;

public interface UserDao {
    int insertUser(User user);
    
    int deleteUserById(String userId);
    User selectByUserId(String userId);
    int updateUser(User user);
}
