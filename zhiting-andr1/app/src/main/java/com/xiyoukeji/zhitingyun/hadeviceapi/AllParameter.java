package com.xiyoukeji.zhitingyun.hadeviceapi;


import cn.earhear.hadevicelib.HAParameter;

/**
 * Created by ice88 on 2018-5-23.
 */

public class AllParameter {
    HAParameter parameter;
    String type;

    public AllParameter(HAParameter parameter, String type) {
        this.parameter = parameter;
        this.type = type;
    }

    public HAParameter getParameter() {
        return parameter;
    }

    public String getType() {
        return type;
    }
}
