package com.example.rpc.rpc;

/**
 * @author Don
 * @date 2021/6/16.
 */
public class Result<T>{
    private String code;
    private T data;

    public static <T> Result<T> success(T data){
        Result<T> r = new Result<>();
        r.setCode("0000");
        r.setData(data);
        return r;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
