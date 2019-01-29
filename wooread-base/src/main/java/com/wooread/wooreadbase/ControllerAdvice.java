package com.wooread.wooreadbase;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadbase.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.wooread.wooreadbase.jwt.JwtUtils.TOKEN_HEADER_NAME;

@org.springframework.web.bind.annotation.ControllerAdvice(basePackages =
        {"com.wooread.wooreadnovel.controller",
                "com.wooread.wooreaduser.controller"})
public class ControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler
    @ResponseBody
    public Object exceptionHandler(Throwable e) {
        logger.warn(e.getMessage());
        return new BaseServiceOutput<>(BaseServiceOutput.CODE_EXCEPTION, e.getMessage(), null);
    }

    /***
     * 从请求中获取JwtToken,这里不检查有效性和过期时间（在网关已经检查过了）
     * */
    @ModelAttribute
    public JwtUtils.DecodedToken decodedToken(@RequestHeader(value = TOKEN_HEADER_NAME, required = false) String token) {
        if (StringUtils.isEmpty(token))
            return null;
        return new JwtUtils.DecodedToken(token);
    }

}
