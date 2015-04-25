package com.it.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table USER_INFO.
 */
public class UserInfo {

    private Long id;
    private String nickname;
    private String password;
    private String mobile;
    private String portrait;
    private Integer user_type;
    private Integer user_level;
    private String personal_data;
    private Integer is_valid;
    private Integer is_famous_expert;
    private java.util.Date insert_time;

    public UserInfo() {
    }

    public UserInfo(Long id) {
        this.id = id;
    }

    public UserInfo(Long id, String nickname, String password, String mobile, String portrait, Integer user_type, Integer user_level, String personal_data, Integer is_valid, Integer is_famous_expert, java.util.Date insert_time) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.mobile = mobile;
        this.portrait = portrait;
        this.user_type = user_type;
        this.user_level = user_level;
        this.personal_data = personal_data;
        this.is_valid = is_valid;
        this.is_famous_expert = is_famous_expert;
        this.insert_time = insert_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getUser_level() {
        return user_level;
    }

    public void setUser_level(Integer user_level) {
        this.user_level = user_level;
    }

    public String getPersonal_data() {
        return personal_data;
    }

    public void setPersonal_data(String personal_data) {
        this.personal_data = personal_data;
    }

    public Integer getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(Integer is_valid) {
        this.is_valid = is_valid;
    }

    public Integer getIs_famous_expert() {
        return is_famous_expert;
    }

    public void setIs_famous_expert(Integer is_famous_expert) {
        this.is_famous_expert = is_famous_expert;
    }

    public java.util.Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(java.util.Date insert_time) {
        this.insert_time = insert_time;
    }

}
