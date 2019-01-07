package com.wooread.wooreaduser.service;

import com.wooread.wooreaduser.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.RoleServiceInput;
import com.wooread.wooreaduser.model.Role;

import java.util.List;

public interface RoleService {
    BaseServiceOutput<Role> createRole(RoleServiceInput.CreateRoleInput input);

    BaseServiceOutput<Boolean> deleteRoleById(Integer roleId);

    BaseServiceOutput<Role> updateRole(RoleServiceInput.UpdateRoleInput input);

    BaseServiceOutput<List<Role>> findAllRole();

    BaseServiceOutput<Boolean> isValidRoleId(String roleIds);
}
