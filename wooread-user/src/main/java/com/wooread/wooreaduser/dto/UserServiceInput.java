package com.wooread.wooreaduser.dto;

import com.wooread.wooreadbase.validation.Phone;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
        @Phone
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
        private String userId;
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
        private String userId;
        @Email
        private String email;
        @Phone
        private String phone;
        private Byte gender;
        private String userRoleIds;
    }
}
