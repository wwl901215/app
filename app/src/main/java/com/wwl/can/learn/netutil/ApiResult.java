package com.wwl.can.learn.netutil;

import android.text.TextUtils;

/**
 * 封装的通用服务器返回对象，可自行定义
 * Created by wangwenliang on 2017/12/17.
 */

public class ApiResult<T> {
    private int code;
    private String message;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public ApiResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return TextUtils.isEmpty(msg) ? message : msg;
    }

    public ApiResult setMessage(String msg) {
        this.message = msg;
        return this;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public ApiResult setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
