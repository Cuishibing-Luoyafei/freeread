package com.wooread.wooreaduser;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = {"com.wooread.wooreaduser.controller"})
public class ControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
    @ExceptionHandler
    @ResponseBody
    public Object JwtTokenException(Throwable e) {
        logger.error(e.getMessage());
        return new BaseServiceOutput<>(BaseServiceOutput.CODE_EXCEPTION, e.getMessage(), null);
    }

}
