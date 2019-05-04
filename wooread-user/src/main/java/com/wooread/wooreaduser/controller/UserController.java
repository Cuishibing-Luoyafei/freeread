package com.wooread.wooreaduser.controller;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.LoginServiceInput;
import com.wooread.wooreaduser.dto.UserServiceInput;
import com.wooread.wooreaduser.model.User;
import com.wooread.wooreaduser.model.UserInfo;
import com.wooread.wooreaduser.service.LoginService;
import com.wooread.wooreaduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofSuccess;
import static com.wooread.wooreadbase.tools.MessageTools.message;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @GetMapping("existUser")
    public BaseServiceOutput<Boolean> existUser(@RequestParam("userId") String userId) {
        return ofSuccess(() -> userService.findUserById(userId).getPayload() != null);
    }

    @GetMapping("findUserLikeName")
    public BaseServiceOutput<List<User>> findUserLikeName(@RequestParam(value = "name") String name) {
        return userService.findUserLikeName(name);
    }

    @GetMapping("findUserByName")
    public BaseServiceOutput<User> findUserByName(@RequestParam("name") String name) {
        return userService.findUserByName(name);
    }

    @PostMapping(value = "createUser")
    public BaseServiceOutput<User> createUser(@RequestBody @Validated UserServiceInput.CreateUserInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ofFail(message(bindingResult.getFieldError()));
        }
        return userService.createUser(input);
    }

    @PostMapping(value = "updateUserInfo")
    public BaseServiceOutput<UserInfo> updateUserInfo(@RequestBody @Validated UserServiceInput.UpdateUserInfoInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ofFail(message(bindingResult.getFieldError()));
        }
        return userService.updateUserInfo(input);
    }

    @PostMapping("generateJwtToken")
    public BaseServiceOutput<String> generateJwtToken(@RequestBody @Validated LoginServiceInput.GenerateJwtTokenInput input,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ofFail(message(bindingResult.getFieldError()));
        }
        return loginService.generateJwtToken(input);
    }
}
