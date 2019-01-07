package com.wooread.wooreaduser.controller;

import com.wooread.wooreaduser.dto.BaseServiceOutput;
import com.wooread.wooreaduser.model.User;
import com.wooread.wooreaduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("findUserLikeName")
    public BaseServiceOutput<List<User>> findUserLikeName(@RequestParam(value = "name",required = false) String name){
        if(true){
            throw new RuntimeException("df");
        }
        if(name == null){
            return new BaseServiceOutput<>(-1,"name not be null",null);
        }
        return userService.findUserLikeName(name);
    }


}
