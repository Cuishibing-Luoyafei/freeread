package com.wooread.wooreaduser.service.impl;

import com.wooread.wooreaduser.dto.BaseServiceOutput;
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

import static com.wooread.wooreaduser.dto.BaseServiceOutput.CODE_FAIL;
import static com.wooread.wooreaduser.dto.BaseServiceOutput.CODE_SUCCESS;

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
            return new BaseServiceOutput<>(CODE_FAIL, "same role name", null);

        Role role = new Role();
        BeanUtils.copyProperties(input, role);
        role = roleCommonRepository.save(role);

        if (roleCommonRepository.findAll(Specifications.equal("roleName", input.getRoleName())).size() > 1) {
            roleCommonRepository.delete(role);
            return new BaseServiceOutput<>(CODE_FAIL, "same role name", null);
        }
        return new BaseServiceOutput<>(CODE_SUCCESS, "success", role);
    }

    @Override
    public BaseServiceOutput<?> deleteRoleById(Integer roleId) {
        roleCommonRepository.deleteById(roleId);
        return new BaseServiceOutput<>(CODE_SUCCESS, "success", null);
    }

    @Override
    public BaseServiceOutput<Role> updateRole(RoleServiceInput.UpdateRoleInput input) {
        return roleCommonRepository.findById(input.getRoleId()).map(role -> {
            BeanUtils.copyProperties(input, role);
            role = roleCommonRepository.save(role);
            return new BaseServiceOutput<>(CODE_SUCCESS, "success", role);
        }).orElse(new BaseServiceOutput<>(CODE_FAIL, "no such role", null));
    }

    @Override
    public BaseServiceOutput<List<Role>> findAllRole() {
        return new BaseServiceOutput<>(CODE_SUCCESS, "success", roleCommonRepository.findAll());
    }

    @Override
    public BaseServiceOutput<Boolean> isValidRoleId(String roleIds) {
        BaseServiceOutput<Boolean> result = new BaseServiceOutput<>(CODE_SUCCESS, "success", true);
        if (StringUtils.isEmpty(roleIds)) {
            return result;
        }
        char[] chars = roleIds.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c) && c != ',') {
                result.setData(false);
                result.setMessage("输入中含有其他字符");
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
                result.setMessage("不存在的role id");
                return result;
            }
        }
        return result;
    }
}
