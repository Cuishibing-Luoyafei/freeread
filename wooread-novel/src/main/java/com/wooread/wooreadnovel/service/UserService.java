package com.wooread.wooreadnovel.service;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * wooread-user微服务暴露出的接口
 */
@FeignClient(value = "wooread-user")
public interface UserService {

    @GetMapping("existUser")
    BaseServiceOutput<Boolean> existUser(@RequestParam("userId") String userId);

}
