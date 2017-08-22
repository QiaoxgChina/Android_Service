package com.qiaoxg.servicedemo.bean;

/**
 * Created by admin on 2017/8/22.
 */

public class MessageBean {

    int type;//receive or send
    int headerId;
    String content;
    String title;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHeaderId() {
        return headerId;
    }

    public void setHeaderId(int headerId) {
        this.headerId = headerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
