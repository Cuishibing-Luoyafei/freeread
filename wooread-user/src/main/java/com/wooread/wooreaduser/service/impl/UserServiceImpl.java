package com.wooread.wooreaduser.service.impl;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.UserServiceInput;
import com.wooread.wooreaduser.model.User;
import com.wooread.wooreaduser.model.UserInfo;
import com.wooread.wooreaduser.service.RoleService;
import com.wooread.wooreaduser.service.UserService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.*;
import static com.wooread.wooreadbase.tools.MessageTools.message;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource(name = "User")
    private CommonRepository<User, Integer> userCommonRepository;

    @Resource(name = "UserInfo")
    private CommonRepository<UserInfo, Integer> userInfoCommonRepository;

    @Autowired
    private RoleService roleService;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public BaseServiceOutput<User> createUser(UserServiceInput.CreateUserInput input) {
        List<User> users = userCommonRepository.findAll(Specifications.equal("userName", input.getUserName()));
        if (users.size() > 0)
            return ofFail(message("duplicate", "user"));

        BaseServiceOutput<Boolean> validateResult = roleService.isValidRoleId(input.getUserRoleIds());
        if (!validateResult.getData())
            return ofFail(validateResult.getMessage());

        User user = new User();
        BeanUtils.copyProperties(input, user);
        user = userCommonRepository.save(user);

        List<User> userName = userCommonRepository.findAll(Specifications.equal("userName", user.getUserName()));
        if (userName.size() > 1) {// 用户名重复
            // 把这条信息删除
            userCommonRepository.delete(user);
            return ofFail(message("duplicate", "user"));
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        BeanUtils.copyProperties(input, userInfo);
        userInfoCommonRepository.save(userInfo);
        user.setUserInfoId(userInfo.getUserInfoId());
        userCommonRepository.save(user);
        return ofSuccess(user);
    }

    @Override
    public BaseServiceOutput<User> updateUser(UserServiceInput.UpdateUserInput input) {
        return userCommonRepository.findById(input.getUserId()).map(user -> {
            BeanUtils.copyProperties(input, user);
            userCommonRepository.save(user);
            return ofSuccess(user);
        }).orElse(ofFail(message("no-such", "user")));
    }

    @Override
    public BaseServiceOutput<UserInfo> updateUserInfo(UserServiceInput.UpdateUserInfoInput input) {
        BaseServiceOutput<Boolean> validateResult = roleService.isValidRoleId(input.getUserRoleIds());
        if (validateResult.getData())
            return ofFail(validateResult.getMessage());

        return userCommonRepository.findById(input.getUserId()).map(user -> {
            return userInfoCommonRepository.findById(user.getUserInfoId()).map(userInfo -> {
                BeanUtils.copyProperties(input, userInfo);
                userInfoCommonRepository.save(userInfo);
                return ofSuccess(userInfo);
            }).orElse(ofFail(message("no-such", "user info")));
        }).orElse(ofFail(message("no-such", "user")));
    }

    @Override
    public BaseServiceOutput<List<User>> findUserLikeName(String userName) {
        return ofSuccess(() -> userCommonRepository.findAll(Specifications.like("userName", "%" + userName + "%")));
    }

    @Override
    public BaseServiceOutput<User> findUserByName(String userName) {
        return ofSuccess(() -> userCommonRepository.findOne(Specifications.equal("userName", userName)).orElse(null));
    }

    @Override
    public BaseServiceOutput<UserInfo> findUserInfo(Integer userId) {
        return ofSuccess(() -> userInfoCommonRepository.findOne(Specifications.equal("userId", userId)).orElse(null));
    }

    @Override
    public BaseServiceOutput<User> findUserById(Integer userId) {
        return ofSuccess(() -> userCommonRepository.findById(userId).orElse(null));
    }
}
