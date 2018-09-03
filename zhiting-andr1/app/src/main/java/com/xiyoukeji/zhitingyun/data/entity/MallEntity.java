package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;

public class MallEntity implements Serializable {

    /**
     * id : 14
     * coverPic : images/admin/15310163219160479c1.jpg
     * title : 测试二
     * link : 13213
     */

    private Integer id;
    private String coverPic;
    private String title;
    private String link;
    private Integer pageNo;
    private Integer pageSize;
    private boolean sort;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }
}
