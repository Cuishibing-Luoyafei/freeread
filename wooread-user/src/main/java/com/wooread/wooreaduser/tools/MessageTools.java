package com.wooread.wooreaduser.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class MessageTools {
    private static MessageTools messageTools;

    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    public void init() {
        messageTools = this;
    }

    public static String message(String code,String... args){
        return message(code,args);
    }

    public static String message(String code, Object[] args) {
        return messageTools.messageSource.getMessage(code, args, Locale.getDefault());
    }

}
