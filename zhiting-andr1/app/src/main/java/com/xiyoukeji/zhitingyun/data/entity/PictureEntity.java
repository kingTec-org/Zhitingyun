package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/25.
 */

public class PictureEntity implements Serializable {

    private static final long serialVersionUID = 8001126945858176116L;
    /**
     * create_time : 1516784770634
     * saved : true
     * id : 1
     * type : null
     * url : https://rfc-dev.oss-cn-hangzhou.aliyuncs.com/image/799fcc68-d832-4de1-b09e-d3225e593752.png
     */

    private long create_time;
    private boolean saved;
    private int id;
    private String type;
    private String url;

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
