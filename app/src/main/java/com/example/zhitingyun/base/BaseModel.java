package com.example.zhitingyun.base;

/**
 * Created by zhulinfeng on 2018/2/27.
 */

public class BaseModel<T> {
    private int code;
    private String message;
    private Comeback<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Comeback<T> getData() {
        return data;
    }

    public void setData(Comeback<T> data) {
        this.data = data;
    }
}
