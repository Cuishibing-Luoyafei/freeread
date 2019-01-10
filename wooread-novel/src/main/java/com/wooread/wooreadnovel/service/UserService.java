package com.wooread.wooreadnovel.service;

import com.wooread.wooreadnovel.dto.BaseServiceOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "wooread-user")
public interface UserService {

    @GetMapping("existUser")
    BaseServiceOutput<Boolean> existUser(@RequestParam("userId") Integer userId);

}
