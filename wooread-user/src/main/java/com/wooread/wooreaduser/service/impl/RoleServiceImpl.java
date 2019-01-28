package com.wooread.wooreaduser.service.impl;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.RoleServiceInput;
import com.wooread.wooreaduser.model.Role;
import com.wooread.wooreaduser.service.RoleService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.*;
import static com.wooread.wooreadbase.tools.MessageTools.message;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource(name = "Role")
    private CommonRepository<Role, Integer> roleCommonRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public BaseServiceOutput<Role> createRole(RoleServiceInput.CreateRoleInput input) {
        List<Role> roles = roleCommonRepository.findAll(Specifications.equal("roleName", input.getRoleName()));
        if (roles.size() > 0)
            return ofFail(message("duplicate", "role name"));

        Role role = new Role();
        BeanUtils.copyProperties(input, role);
        role = roleCommonRepository.save(role);

        if (roleCommonRepository.findAll(Specifications.equal("roleName", input.getRoleName())).size() > 1) {
            roleCommonRepository.delete(role);
            return ofFail(message("duplicate", "role name"));
        }
        return ofSuccess(role);
    }

    @Override
    public BaseServiceOutput<Boolean> deleteRoleById(Integer roleId) {
        return ofSuccess(() -> {
            roleCommonRepository.deleteById(roleId);
            return true;
        });
    }

    @Override
    public BaseServiceOutput<Role> updateRole(RoleServiceInput.UpdateRoleInput input) {
        return roleCommonRepository.findById(input.getRoleId()).map(role -> {
            List<Role> allRoles = roleCommonRepository.findAll(Specifications.equal("roleName", input.getRoleName()));
            for (Role r : allRoles) {
                if (r.getRoleName().equals(input.getRoleName()) && !r.getRoleId().equals(role.getRoleId())) {
                    return ofFail(message("duplicate", "role name"), (Role) null);
                }
            }
            BeanUtils.copyProperties(input, role);
            role = roleCommonRepository.save(role);
            return ofSuccess(role);
        }).orElse(ofFail(message("no-such", "role")));
    }

    @Override
    public BaseServiceOutput<List<Role>> findAllRole() {
        return ofSuccess(roleCommonRepository.findAll());
    }

    @Override
    public BaseServiceOutput<Boolean> isValidRoleId(String roleIds) {
        BaseServiceOutput<Boolean> result = new BaseServiceOutput<>(CODE_SUCCESS, message("success"), true);
        if (StringUtils.isEmpty(roleIds)) {
            return result;
        }
        char[] chars = roleIds.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c) && c != ',') {
                result.setData(false);
                result.setMessage(message("invalid-role-ids"));
                return result;
            }
        }
        String[] splitIds = roleIds.split(",");
        Set<Integer> roleIdSet = new HashSet<>();
        List<Role> allRole = roleCommonRepository.findAll();
        allRole.forEach((role) -> roleIdSet.add(role.getRoleId()));
        for (String idStr : splitIds) {
            if (!roleIdSet.contains(Integer.parseInt(idStr))) {
                result.setData(false);
                result.setMessage(message("no-such", "role id"));
                return result;
            }
        }
        return result;
    }
}
