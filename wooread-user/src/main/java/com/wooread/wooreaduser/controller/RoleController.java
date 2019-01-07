package com.wooread.wooreaduser.controller;

import com.wooread.wooreaduser.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.RoleServiceInput;
import com.wooread.wooreaduser.model.Role;
import com.wooread.wooreaduser.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wooread.wooreaduser.tools.MessageTools.message;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("createRole")
    public BaseServiceOutput<?> createRole(@Validated RoleServiceInput.CreateRoleInput input,
                                              BindingResult bindingResult){
        BaseServiceOutput<?> result = new BaseServiceOutput<>(BaseServiceOutput.CODE_FAIL,"");
        if(bindingResult.hasErrors()){
            result.setMessage(message(bindingResult.getFieldError()));
            return result;
        }
        result = roleService.createRole(input);
        return result;
    }

    @PostMapping("updateRole")
    public BaseServiceOutput<?> updateRole(@Validated RoleServiceInput.UpdateRoleInput input,
                                           BindingResult bindingResult){
        BaseServiceOutput<?> result = new BaseServiceOutput<>(BaseServiceOutput.CODE_FAIL,"");
        if(bindingResult.hasErrors()){
            result.setMessage(message(bindingResult.getFieldError()));
            return result;
        }
        result = roleService.updateRole(input);
        return result;
    }
}
