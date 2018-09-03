package com.xiyoukeji.zhitingyun.data.entity;

/**
 * Created by dasiy on 2018/7/25.
 */

public class SocketMessage {
    private Integer code;
    private ReturnMessage message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ReturnMessage getMessage() {
        return message;
    }

    public void setMessage(ReturnMessage message) {
        this.message = message;
    }
}
