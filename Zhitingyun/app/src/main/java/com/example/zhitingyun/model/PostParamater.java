package com.example.zhitingyun.model;

import java.util.List;

/**
 * Created by dasiy on 2018/7/25.
 */

public class PostParamater {
    private Integer code;
    private List<EquipmentParamater> message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<EquipmentParamater> getMessage() {
        return message;
    }

    public void setMessage(List<EquipmentParamater> message) {
        this.message = message;
    }
}
