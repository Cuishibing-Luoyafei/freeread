package com.wooread.wooreadbase.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wooread.wooreadbase.tools.MessageTools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.wooread.wooreadbase.tools.MessageTools.message;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseServiceOutput<T> implements Serializable {
    public static final int CODE_SUCCESS = 1 << 1;
    public static final int CODE_FAIL = 1 << 2;
    public static final int CODE_EXCEPTION = 1 << 3;

    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAIL = "fail";
    public static final String MSG_EXCEPTION = "exception";

    private int code;
    private String messageCode;
    private String message;
    private Object messageArgs;
    private T payload;

    public BaseServiceOutput(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseServiceOutput(int code, String message, T payload) {
        this(code, message);
        this.payload = payload;
    }

    public BaseServiceOutput(int code, String message, Supplier<T> supplier) {
        this(code, message);
        if (supplier != null)
            this.payload = supplier.get();
    }

    public BaseServiceOutput(int code, MessageTools.MessageInfo messageInfo) {
        this(code, messageInfo.getMessage());
        this.messageCode = messageInfo.getMessageCode();
        this.messageArgs = messageInfo.getArgs();
    }

    public BaseServiceOutput(int code, MessageTools.MessageInfo messageInfo, T payload) {
        this(code, messageInfo);
        this.payload = payload;
    }

    public BaseServiceOutput(int code, MessageTools.MessageInfo messageInfo, Supplier<T> supplier) {
        this(code, messageInfo);
        if (supplier != null)
            this.payload = supplier.get();
    }

    public static <T> BaseServiceOutput<T> ofSuccess(Supplier<T> supplier) {
        return new BaseServiceOutput<>(CODE_SUCCESS, message(MSG_SUCCESS), supplier);
    }

    public static <T> BaseServiceOutput<T> ofSuccess(T data) {
        return new BaseServiceOutput<>(CODE_SUCCESS, message(MSG_SUCCESS), data);
    }

    public static <T> BaseServiceOutput<T> ofFail(String message) {
        return new BaseServiceOutput<>(CODE_FAIL, message);
    }

    public static <T> BaseServiceOutput<T> ofFail(MessageTools.MessageInfo message) {
        return new BaseServiceOutput<>(CODE_FAIL, message);
    }

    public static <T> BaseServiceOutput<T> ofFail(String message, T data) {
        return new BaseServiceOutput<>(CODE_FAIL, message, data);
    }

    public static <T> BaseServiceOutput<T> ofFail(String message, Supplier<T> supplier) {
        return new BaseServiceOutput<>(CODE_FAIL, message, supplier);
    }

    public static <T> BaseServiceOutput<T> ofFail(MessageTools.MessageInfo message, T data) {
        return new BaseServiceOutput<>(CODE_FAIL, message, data);
    }

    public static <T> BaseServiceOutput<T> ofFail(MessageTools.MessageInfo message, Supplier<T> supplier) {
        return new BaseServiceOutput<>(CODE_FAIL, message, supplier);
    }

    public static <T> BaseServiceOutput<T> ofException(String message) {
        return new BaseServiceOutput<>(CODE_EXCEPTION, message);
    }

    public static <T> BaseServiceOutput<T> ofException(MessageTools.MessageInfo message) {
        return new BaseServiceOutput<>(CODE_EXCEPTION, message);
    }

    public static <T> BaseServiceOutput<T> ofException(String message, T data) {
        return new BaseServiceOutput<>(CODE_EXCEPTION, message, data);
    }

    public static <T> BaseServiceOutput<T> ofException(String message, Supplier<T> supplier) {
        return new BaseServiceOutput<>(CODE_EXCEPTION, message, supplier);
    }

    public static <T> BaseServiceOutput<T> ofException(MessageTools.MessageInfo message, T data) {
        return new BaseServiceOutput<>(CODE_EXCEPTION, message, data);
    }

    public static <T> BaseServiceOutput<T> ofException(MessageTools.MessageInfo message, Supplier<T> supplier) {
        return new BaseServiceOutput<>(CODE_EXCEPTION, message, supplier);
    }

    public <R> Optional<R> ifSuccess(Function<T, R> function) {
        if (success())
            return Optional.ofNullable(function.apply(payload));
        else return Optional.empty();
    }

    public void ifSuccess(Consumer<T> consumer) {
        consumer.accept(payload);
    }

    public boolean success() {
        return code == CODE_SUCCESS;
    }

    public boolean fail() {
        return code == CODE_FAIL;
    }

    public boolean exception() {
        return code == CODE_EXCEPTION;
    }

    @JsonIgnore
    public void setMessage(MessageTools.MessageInfo info) {
        this.messageArgs = info.getArgs();
        this.message = info.getMessage();
        this.messageCode = info.getMessageCode();
    }
}
