package com.design.smartparking.dto;

import org.springframework.http.HttpStatus;

public class Result<T> {

    private int code;

    private String msg;

    private T data;

    private Long count;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(){

    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<T>(-1, msg);
    }

    public static <T> Result<T> fail422(String msg) {
        return new Result<T>(HttpStatus.UNPROCESSABLE_ENTITY.value(), msg);
    }

    public static <T> Result<T> fail403(String msg) {
        return new Result<T>(HttpStatus.FORBIDDEN.value(), msg);
    }

    public static <T> Result<T> fail401(String msg) {
        return new Result<T>(HttpStatus.UNAUTHORIZED.value(), msg);
    }

    public static <T> Result<T> success() {
        return new Result<T>(HttpStatus.OK.value(), "success");
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(HttpStatus.OK.value(), "success", data);
    }
    public static <T> Result<T> success(T data,Long count){
        Result<T> ok = new Result<>(HttpStatus.OK.value(), "success", data);
        ok.setCount(count);
        return ok;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
