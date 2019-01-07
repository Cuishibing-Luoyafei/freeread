package com.wooread.wooreaduser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RoleServiceInput implements Serializable {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRoleInput {
        @NotNull
        @Length(min = 1,max = 10)
        private String roleName;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateRoleInput extends CreateRoleInput {
        @NotNull
        private Integer roleId;
    }
}
