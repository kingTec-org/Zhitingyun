package com.xiyoukeji.zhitingyun.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/5.
 */

public class UploadEntity implements Serializable {

    private static final long serialVersionUID = 371766784375278927L;
    private PictureEntity data;

    public PictureEntity getData() {
        return data;
    }

    public void setData(PictureEntity data) {
        this.data = data;
    }
}
