package com.lee.mvpstudy.http;

public class ApiException extends IllegalArgumentException {

    private String code;

    public ApiException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
