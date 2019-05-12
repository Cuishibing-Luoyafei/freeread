package com.wooread.wooreaduser.service;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreaduser.dto.LoginServiceInput;

public interface LoginService {

    BaseServiceOutput<String> generateJwtToken(LoginServiceInput.GenerateJwtTokenInput input);

}
