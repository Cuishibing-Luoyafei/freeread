package com.wooread.wooreaduser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseServiceOutput<T> implements Serializable {
    public static final int CODE_SUCCESS = 1 << 1;
    public static final int CODE_FAIL = 1 << 2;
    public static final int CODE_EXCEPTION = 1 << 3;

    private int code;
    private String message;
    private T data;

    public BaseServiceOutput(int code,String message,Supplier<T> supplier){
        this(code,message);
        this.data = supplier.get();
    }

    public BaseServiceOutput(int code,String message){
        this.code = code;
        this.message = message;
    }

    public <R> R ifSuccess(Function<T,R> function){
        return function.apply(data);
    }

    public void ifSuccess(Consumer<T> consumer){
        consumer.accept(data);
    }

    public boolean isSuccess() {
        return code == CODE_SUCCESS;
    }

    public boolean isFail() {
        return code == CODE_FAIL;
    }

    public boolean isException() {
        return code == CODE_EXCEPTION;
    }
}
