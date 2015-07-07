package com.yingluo.Appraiser.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table CONTENT_INFO.
 */
public class ContentInfo implements Serializable{


	@SerializedName("id")
    private Long id;
	
	@SerializedName("title")
    private String title;
	
	@SerializedName("content")
    private String content;
	
	@SerializedName("content_type")
    private Integer content_type;
	
	@SerializedName("content_classify_id")
    private Integer content_classify_id;
	
	@SerializedName("admin_name")
    private String admin_name;
	
	@SerializedName("admin_id")
    private Long admin_id;
	
	@SerializedName("is_valid")
    private Integer is_valid;
	
	@SerializedName("is_hot")
    private String is_hot;
	
	@SerializedName("insert_time")
    private String insert_time;
	@SerializedName("image")
    private String image;
	
	@SerializedName("viewTimes")
    private Integer view_times;
	
	
	@SerializedName("isCollected")
    private Integer isCollected;


    public ContentInfo() {
    }

    public ContentInfo(Long id) {
        this.id = id;
    }

    public ContentInfo(Long id, String title, String content, Integer content_type, Integer content_classify_id, String admin_name, Long admin_id, Integer is_valid, String is_hot, String insert_time, String image, Integer view_times, Integer isCollected) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.content_type = content_type;
        this.content_classify_id = content_classify_id;
        this.admin_name = admin_name;
        this.admin_id = admin_id;
        this.is_valid = is_valid;
        this.is_hot = is_hot;
        this.insert_time = insert_time;
        this.image = image;
        this.view_times = view_times;
        this.isCollected = isCollected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContent_type() {
        return content_type;
    }

    public void setContent_type(Integer content_type) {
        this.content_type = content_type;
    }

    public Integer getContent_classify_id() {
        return content_classify_id;
    }

    public void setContent_classify_id(Integer content_classify_id) {
        this.content_classify_id = content_classify_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
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

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getView_times() {
        return view_times;
    }

    public void setView_times(Integer view_times) {
        this.view_times = view_times;
    }

    public Integer getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(Integer isCollected) {
        this.isCollected = isCollected;
    }
    @Override
        public boolean equals(Object o) {
      	// TODO Auto-generated method stub
       	if(o instanceof ContentInfo){
     		ContentInfo c=(ContentInfo) o;
      		if(c.getId()==id){
      			return true;
      		}
      	}
       	return super.equals(o);
       }
}
