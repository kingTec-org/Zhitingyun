package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;

public class MessageEntity implements Serializable{

    /**
     * newsId : 42
     * newsType : 3
     * status : 1
     * content : <p>111111111222233edffgghjlo<img src="../../images/admin/15323173044457eb315.jpg" alt="" width="200" height="200" />hjgjhghjghjgghjg<img src="../../images/admin/1532317325973ca855a.jpg" alt="" width="412" height="213" /></p>
     * coverPic : images/admin/1532317335321b5d11f.jpg
     * title : 111
     * createTime : 1532317340455
     */

    private Integer newsId;
    private Integer newsType;
    private Integer status;
    private String content;
    private String coverPic;
    private String title;
    private long createTime;
    private long lastTime;
    private String richText;
    private long lastPushTime;
    private boolean pushUser;
    private boolean pushProfessor;
    private boolean hadPublish;
    private boolean del;
    private String reply;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }

    public long getLastPushTime() {
        return lastPushTime;
    }

    public void setLastPushTime(long lastPushTime) {
        this.lastPushTime = lastPushTime;
    }

    public boolean isPushUser() {
        return pushUser;
    }

    public void setPushUser(boolean pushUser) {
        this.pushUser = pushUser;
    }

    public boolean isPushProfessor() {
        return pushProfessor;
    }

    public void setPushProfessor(boolean pushProfessor) {
        this.pushProfessor = pushProfessor;
    }

    public boolean isHadPublish() {
        return hadPublish;
    }

    public void setHadPublish(boolean hadPublish) {
        this.hadPublish = hadPublish;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
