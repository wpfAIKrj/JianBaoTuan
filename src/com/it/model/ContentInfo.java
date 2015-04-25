package com.it.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table CONTENT_INFO.
 */
public class ContentInfo {

    private Long id;
    private String content_data;
    private Integer content_type;
    private String content_classify_name;
    private Integer is_valid;
    private String is_hot;
    private java.util.Date insert_time;

    public ContentInfo() {
    }

    public ContentInfo(Long id) {
        this.id = id;
    }

    public ContentInfo(Long id, String content_data, Integer content_type, String content_classify_name, Integer is_valid, String is_hot, java.util.Date insert_time) {
        this.id = id;
        this.content_data = content_data;
        this.content_type = content_type;
        this.content_classify_name = content_classify_name;
        this.is_valid = is_valid;
        this.is_hot = is_hot;
        this.insert_time = insert_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent_data() {
        return content_data;
    }

    public void setContent_data(String content_data) {
        this.content_data = content_data;
    }

    public Integer getContent_type() {
        return content_type;
    }

    public void setContent_type(Integer content_type) {
        this.content_type = content_type;
    }

    public String getContent_classify_name() {
        return content_classify_name;
    }

    public void setContent_classify_name(String content_classify_name) {
        this.content_classify_name = content_classify_name;
    }

    public Integer getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(Integer is_valid) {
        this.is_valid = is_valid;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public java.util.Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(java.util.Date insert_time) {
        this.insert_time = insert_time;
    }

}
