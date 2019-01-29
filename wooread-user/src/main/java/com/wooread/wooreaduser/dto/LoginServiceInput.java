package com.wooread.wooreaduser.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class LoginServiceInput {
    @Data
    public static class GenerateJwtTokenInput {
        @NotEmpty
        @Length(min = 5, max = 50)
        private String userName;
        @NotEmpty
        @Length(min = 5, max = 50)
        private String password;
    }
}
