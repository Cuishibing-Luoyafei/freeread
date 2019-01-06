package com.wooread.wooreaduser.dto;

import lombok.*;

import java.io.Serializable;

public class UserServiceInput {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateUserInput implements Serializable {
        private String userName;
        private String password;
        private String email;
        private String phone;
        private Byte gender;
        private String userRoleIds;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateUserInput extends CreateUserInput {
        private Integer userId;
    }
}
