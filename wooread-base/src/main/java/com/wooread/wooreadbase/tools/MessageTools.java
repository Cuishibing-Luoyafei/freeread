package com.wooread.wooreadbase.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

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

    public static String message(String code, String... args) {
        return messageTools.messageSource.getMessage(code, args, Locale.getDefault());
    }

    public static String message(String code, Object... args) {
        return messageTools.messageSource.getMessage(code, args, Locale.getDefault());
    }

    public static String message(FieldError fieldError) {
        if (fieldError != null)
            return messageTools.messageSource.getMessage(fieldError.getCode(), new Object[]{fieldError.getField()},
                    fieldError.getField() + ":" + fieldError.getDefaultMessage(), Locale.getDefault());
        return "";
    }

}
