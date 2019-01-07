package com.wooread.wooreaduser.controller;

import com.wooread.wooreaduser.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.UserServiceInput;
import com.wooread.wooreaduser.model.User;
import com.wooread.wooreaduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.wooread.wooreaduser.dto.BaseServiceOutput.CODE_FAIL;
import static com.wooread.wooreaduser.tools.MessageTools.message;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("findUserLikeName")
    public BaseServiceOutput<List<User>> findUserLikeName(@RequestParam(value = "name") String name) {
        return userService.findUserLikeName(name);
    }

    @GetMapping("findUserByName")
    public BaseServiceOutput<User> findUserByName(@RequestParam("name") String name) {
        return userService.findUserByName(name);
    }

    @PostMapping("createUser")
    public BaseServiceOutput<?> createUser(@Validated UserServiceInput.CreateUserInput input, BindingResult bindingResult) {
        BaseServiceOutput<?> result = new BaseServiceOutput<>(CODE_FAIL, "");
        if (bindingResult.hasErrors()) {
            result.setMessage(message(bindingResult.getFieldError()));
            return result;
        }
        result = userService.createUser(input);
        result.setData(null);
        return result;
    }

    @PostMapping("updateUserInfo")
    public BaseServiceOutput<?> updateUserInfo(@Validated UserServiceInput.UpdateUserInfoInput input, BindingResult bindingResult){
        BaseServiceOutput<?> result = new BaseServiceOutput<>(CODE_FAIL, "");
        if (bindingResult.hasErrors()) {
            result.setMessage(message(bindingResult.getFieldError()));
            return result;
        }
        result = userService.updateUserInfo(input);
        result.setData(null);
        return result;
    }
}
