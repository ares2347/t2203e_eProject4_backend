package com.eproject.webapi;

public class BaseResponse<T> {
    public boolean isSucceed;
    public String message;
    public T data;

    public BaseResponse(T data) {
        this.isSucceed = true;
        this.data = data;
    }

    public BaseResponse(String message) {
        this.isSucceed = false;
        this.message = message;
    }
}
