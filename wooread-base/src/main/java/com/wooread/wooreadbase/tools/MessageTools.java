package com.wooread.wooreadbase.tools;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageInfo {
        private String messageCode;
        private String message;
        private Object args;
    }

    public static MessageInfo message(String code, String... args) {
        String message = messageTools.messageSource.getMessage(code, args, "", Locale.getDefault());
        return new MessageInfo(code, message, args);
    }

    public static MessageInfo message(String code, Object... args) {
        String message = messageTools.messageSource.getMessage(code, args, "", Locale.getDefault());
        return new MessageInfo(code, message, args);
    }

    public static MessageInfo message(FieldError fieldError) {
        String message = "";
        String messageCode = "";
        Object args = null;
        if (fieldError != null) {
            messageCode = fieldError.getCode();
            if (messageCode == null)
                messageCode = "";
            args = fieldError.getField();
            message = messageTools.messageSource.getMessage(messageCode, new Object[]{args},
                    fieldError.getField() + ":" + fieldError.getDefaultMessage(), Locale.getDefault());

        }
        return new MessageInfo(messageCode, message, args);
    }

}
