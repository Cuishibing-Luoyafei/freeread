package com.wooread.wooreaduser.controller;

import com.wooread.wooreaduser.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.UserServiceInput;
import com.wooread.wooreaduser.model.User;
import com.wooread.wooreaduser.model.UserInfo;
import com.wooread.wooreaduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wooread.wooreaduser.dto.BaseServiceOutput.*;
import static com.wooread.wooreaduser.tools.MessageTools.message;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("existUser")
    public BaseServiceOutput<Boolean> existUser(@RequestParam("userId") Integer userId) {
        return ofSuccess(() -> userService.findUserById(userId).getData() != null);
    }

    @GetMapping("findUserLikeName")
    public BaseServiceOutput<List<User>> findUserLikeName(@RequestParam(value = "name") String name) {
        return userService.findUserLikeName(name);
    }

    @GetMapping("findUserByName")
    public BaseServiceOutput<User> findUserByName(@RequestParam("name") String name) {
        return userService.findUserByName(name);
    }

    @PostMapping("createUser")
    public BaseServiceOutput<User> createUser(@Validated UserServiceInput.CreateUserInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ofFail(message(bindingResult.getFieldError()));
        }
        return userService.createUser(input);
    }

    @PostMapping("updateUserInfo")
    public BaseServiceOutput<UserInfo> updateUserInfo(@Validated UserServiceInput.UpdateUserInfoInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ofFail(message(bindingResult.getFieldError()));
        }
        return userService.updateUserInfo(input);
    }
}
