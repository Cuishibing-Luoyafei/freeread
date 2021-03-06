package com.wooread.wooreaduser.service;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.UserServiceInput;
import com.wooread.wooreaduser.model.User;
import com.wooread.wooreaduser.model.UserInfo;

import java.util.List;

public interface UserService {
    BaseServiceOutput<User> createUser(UserServiceInput.CreateUserInput input);

    BaseServiceOutput<User> updateUser(UserServiceInput.UpdateUserInput input);

    BaseServiceOutput<UserInfo> findUserInfo(String userId);

    BaseServiceOutput<UserInfo> updateUserInfo(UserServiceInput.UpdateUserInfoInput input);

    BaseServiceOutput<List<User>> findUserLikeName(String userName);

    BaseServiceOutput<User> findUserByName(String userName);

    BaseServiceOutput<User> findUserById(String userId);
}
