package cui.shibing.freeread.service;

import cui.shibing.freeread.model.User;

public interface UserService {
    User getUserById(String userId);
    User getUserByName(String userName);
    int deleteUserById(String userId);
    int updateUserById(User user);
}
