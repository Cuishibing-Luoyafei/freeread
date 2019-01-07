package com.wooread.wooreaduser.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserServiceInput {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateUserInput implements Serializable {
        @NotNull
        @Length(min = 5,max = 50)
        private String userName;
        @NotNull
        @Length(min = 10,max = 50)
        private String password;
        @Email
        private String email;
        private String phone;
        private Byte gender;
        private String userRoleIds;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateUserInput {
        @NotNull
        private Integer userId;
        @NotNull
        @Length(min = 5,max = 50)
        private String userName;
        @NotNull
        @Length(min = 10,max = 50)
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateUserInfoInput{
        @NotNull
        private Integer userId;
        @Email
        private String email;
        private String phone;
        private Byte gender;
        private String userRoleIds;
    }
}
