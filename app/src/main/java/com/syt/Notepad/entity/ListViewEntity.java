package com.syt.Notepad.entity;

import java.io.Serializable;

//implements Serializable 实现序列化接口
public class ListViewEntity implements Serializable {

    private int id;
    private String text_title_of_item;
    private String text_content_of_item;
    private String dtimes;

    public ListViewEntity() {
        super();
    }    //默认构造函数

    public ListViewEntity(String text_title_of_item, String text_content_of_item) {
        super();
        this.text_title_of_item = text_title_of_item;
        this.text_content_of_item = text_content_of_item;
    }    //带标题和内容的构造函数

    public ListViewEntity(String text_title_of_item,
                          String text_content_of_item, String dtimes) {
        super();
        this.text_title_of_item = text_title_of_item;
        this.text_content_of_item = text_content_of_item;
        this.dtimes = dtimes;
    }   //带标题 内容和记录时间的构造函数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDtimes() {
        return dtimes;
    }

    public void setDtimes(String dtimes) {
        this.dtimes = dtimes;
    }

    public String getText_title_of_item() {
        return text_title_of_item;
    }

    public void setText_title_of_item(String text_title_of_item) {
        this.text_title_of_item = text_title_of_item;
    }

    public String getText_content_of_item() {
        return text_content_of_item;
    }

    public void setText_content_of_item(String text_content_of_item) {
        this.text_content_of_item = text_content_of_item;
    }

}
