package com.xiyoukeji.zhitingyun.data.entity;

public class LunBoEntity {

    /**
     * id : 33
     * coverPic : images/admin/1531308612157b078d5.jpg
     * link : www.xiyoukeji.com
     * weight : 34
     */

    private Integer id;
    private String coverPic;
    private String link;
    private Integer weight;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
