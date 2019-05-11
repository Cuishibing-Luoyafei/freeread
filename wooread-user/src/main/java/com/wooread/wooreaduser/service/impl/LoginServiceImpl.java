package com.wooread.wooreaduser.service.impl;

import com.google.gson.Gson;
import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadbase.jwt.JwtUtils;
import com.wooread.wooreaduser.dto.LoginServiceInput;
import com.wooread.wooreaduser.model.User;
import com.wooread.wooreaduser.service.LoginService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofSuccess;
import static com.wooread.wooreadbase.jwt.JwtUtils.SUBJECT_JAVA_CLASS;
import static com.wooread.wooreadbase.tools.MessageTools.message;
import static com.wooread.wooreaduser.message.Msg.LoginMsg.WRONG_PASSWORD;
import static com.wooread.wooreaduser.message.Msg.UserMsg.NO_SUCH_USER;

@Service
public class LoginServiceImpl implements LoginService {

    /**
     * JwtToken 过期时间，默认为一个小时
     */
    private long tokenExpTime = 1000L * 60 * 60;// 1h

    @Resource(name = "User")
    private CommonRepository<User, Integer> userCommonRepository;

    @Override
    public BaseServiceOutput<String> generateJwtToken(LoginServiceInput.GenerateJwtTokenInput input) {
        return userCommonRepository.findOne(Specifications.equal("userName",input.getUserName())).map(user -> {
            if (!user.getPassword().equals(input.getPassword())) {
                return ofFail(message(WRONG_PASSWORD.toString()),input.getPassword());
            }
            JwtUtils.TokenBuilder tokenBuilder = new JwtUtils.TokenBuilder();
            tokenBuilder.addSubject(new Gson().toJson(user));
            tokenBuilder.addExpTime(new Date(System.currentTimeMillis() + tokenExpTime));
            tokenBuilder.addClaim(SUBJECT_JAVA_CLASS, User.class.getName());
            return ofSuccess(tokenBuilder.sign());
        }).orElse(ofFail(message(NO_SUCH_USER.toString(), input.getUserName())));
    }
}
