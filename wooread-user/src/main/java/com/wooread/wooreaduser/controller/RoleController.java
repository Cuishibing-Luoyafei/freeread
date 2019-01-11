package com.wooread.wooreaduser.controller;

import com.wooread.wooreaduser.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.RoleServiceInput;
import com.wooread.wooreaduser.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wooread.wooreaduser.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreaduser.tools.MessageTools.message;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("createRole")
    public BaseServiceOutput<?> createRole(@Validated RoleServiceInput.CreateRoleInput input,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ofFail(message(bindingResult.getFieldError()));
        }
        return roleService.createRole(input);
    }

    @PostMapping("updateRole")
    public BaseServiceOutput<?> updateRole(@Validated RoleServiceInput.UpdateRoleInput input,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ofFail(message(bindingResult.getFieldError()));
        }
        return roleService.updateRole(input);
    }
}
