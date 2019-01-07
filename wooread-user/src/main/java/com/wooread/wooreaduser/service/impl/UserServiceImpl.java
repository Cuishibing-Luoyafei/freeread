package com.wooread.wooreaduser.service.impl;

import com.wooread.wooreaduser.dto.BaseServiceOutput;
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
import java.util.Optional;

import static com.wooread.wooreaduser.dto.BaseServiceOutput.CODE_FAIL;
import static com.wooread.wooreaduser.dto.BaseServiceOutput.CODE_SUCCESS;
import static com.wooread.wooreaduser.tools.MessageTools.message;

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
            return new BaseServiceOutput<>(CODE_FAIL, message("duplicate", "user"));

        BaseServiceOutput<Boolean> validateResult = roleService.isValidRoleId(input.getUserRoleIds());
        if (validateResult.getData())
            return new BaseServiceOutput<>(CODE_FAIL, validateResult.getMessage());

        User user = new User();
        BeanUtils.copyProperties(input, user);
        user = userCommonRepository.save(user);

        List<User> userName = userCommonRepository.findAll(Specifications.equal("userName", user.getUserName()));
        if (userName.size() > 1) {// 用户名重复
            // 把这条信息删除
            userCommonRepository.delete(user);
            return new BaseServiceOutput<>(CODE_FAIL, message("duplicate", "user"));
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        BeanUtils.copyProperties(input, userInfo);
        userInfoCommonRepository.save(userInfo);
        user.setUserInfoId(userInfo.getUserInfoId());
        userCommonRepository.save(user);
        return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), user);
    }

    @Override
    public BaseServiceOutput<User> updateUser(UserServiceInput.UpdateUserInput input) {
        return userCommonRepository.findById(input.getUserId()).map(user -> {
            BeanUtils.copyProperties(input, user);
            userCommonRepository.save(user);
            return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), user);
        }).orElse(new BaseServiceOutput<>(CODE_FAIL, message("no-such", "user")));
    }

    @Override
    public BaseServiceOutput<UserInfo> updateUserInfo(UserServiceInput.UpdateUserInfoInput input) {
        BaseServiceOutput<Boolean> validateResult = roleService.isValidRoleId(input.getUserRoleIds());
        if (validateResult.getData())
            return new BaseServiceOutput<>(CODE_FAIL, validateResult.getMessage());

        return userCommonRepository.findById(input.getUserId()).map(user -> {
            return userInfoCommonRepository.findById(user.getUserInfoId()).map(userInfo -> {
                BeanUtils.copyProperties(input, userInfo);
                userInfoCommonRepository.save(userInfo);
                return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), userInfo);
            }).orElse(new BaseServiceOutput<>(CODE_FAIL, message("no-such", "user info")));
        }).orElse(new BaseServiceOutput<>(CODE_FAIL, message("no-such", "user")));
    }

    @Override
    public BaseServiceOutput<List<User>> findUserLikeName(String userName) {
        return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), () -> {
            return userCommonRepository.findAll(Specifications.like("userName", "%" + userName + "%"));
        });
    }

    @Override
    public BaseServiceOutput<User> findUserByName(String userName) {
        return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), () -> {
            return userCommonRepository.findOne(Specifications.equal("userName", userName)).orElse(null);
        });
    }

    @Override
    public BaseServiceOutput<UserInfo> findUserInfo(Integer userId) {
        return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), () -> {
            return userInfoCommonRepository.findOne(Specifications.equal("userId", userId)).orElse(null);
        });
    }
}
