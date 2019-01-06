package com.wooread.wooreaduser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

public class RoleServiceInput implements Serializable {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRoleInput {
        private String roleName;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateRoleInput extends CreateRoleInput {
        private Integer roleId;
    }
}
