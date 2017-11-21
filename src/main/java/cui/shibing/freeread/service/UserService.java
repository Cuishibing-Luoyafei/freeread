package cui.shibing.freeread.service;

import cui.shibing.freeread.model.User;

public interface UserService {
    User getUserByName(String userName);
    int deleteUserByName(String userName);
    int updateUserByName(User user);
}
