package com.it.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table USER_INFO.
 */
public class UserInfo implements Serializable{

	@SerializedName("id")
	private Long id;
	
	
	@SerializedName("nickname")
	private String nickname;
	
	@SerializedName("password")
	private String password;
	
	@SerializedName("mobile")
	private String mobile;
	
	@SerializedName("portrait")
	private String portrait;
	
	@SerializedName("user_type")
	private Integer user_type;
	
	@SerializedName("user_level")
	private Integer user_level;
	
	@SerializedName("personal_data")
	private String personal_data;
	
	@SerializedName("is_valid")
	private Integer is_valid;
	@SerializedName("is_famous_expert")
	private Integer is_famous_expert;


	@SerializedName("session_id")
	private String session_id;


	@SerializedName("insert_time")
    private Integer insert_time;
	@SerializedName("is_system")
    private Integer is_system;
    @SerializedName("is_bind")
    private Integer is_bind;
    
    @SerializedName("avatar")
    private String avatar;
    
    @SerializedName("image_token")
    private String image_token;
    @SerializedName("message_token")
    private String message_token;
    
    @SerializedName("qq")
    private String qq;
    
    @SerializedName("email")
    private String email;
    
    
    @SerializedName("treasure_number")
    private Integer treasure_number;
    @SerializedName("treasure_record_number")
    private Integer treasure_record_number;
    @SerializedName("foot_number")
    private Integer foot_number;

    public UserInfo() {
    }

    public UserInfo(Long id) {
        this.id = id;
    }

    public UserInfo(Long id, String nickname, String password, String mobile, String portrait, Integer user_type, Integer user_level, String personal_data, Integer is_valid, Integer is_famous_expert, Integer is_system, Integer is_bind, Integer insert_time, String session_id, String avatar, String image_token, String message_token, String qq, String email, Integer treasure_number, Integer treasure_record_number, Integer foot_number) {
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
        this.is_system = is_system;
        this.is_bind = is_bind;
        this.insert_time = insert_time;
        this.session_id = session_id;
        this.avatar = avatar;
        this.image_token = image_token;
        this.message_token = message_token;
        this.qq = qq;
        this.email = email;
        this.treasure_number = treasure_number;
        this.treasure_record_number = treasure_record_number;
        this.foot_number = foot_number;
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

    public Integer getIs_system() {
        return is_system;
    }

    public void setIs_system(Integer is_system) {
        this.is_system = is_system;
    }

    public Integer getIs_bind() {
        return is_bind;
    }

    public void setIs_bind(Integer is_bind) {
        this.is_bind = is_bind;
    }

    public Integer getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Integer insert_time) {
        this.insert_time = insert_time;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImage_token() {
        return image_token;
    }

    public void setImage_token(String image_token) {
        this.image_token = image_token;
    }

    public String getMessage_token() {
        return message_token;
    }

    public void setMessage_token(String message_token) {
        this.message_token = message_token;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTreasure_number() {
        return treasure_number;
    }

    public void setTreasure_number(Integer treasure_number) {
        this.treasure_number = treasure_number;
    }

    public Integer getTreasure_record_number() {
        return treasure_record_number;
    }

    public void setTreasure_record_number(Integer treasure_record_number) {
        this.treasure_record_number = treasure_record_number;
    }

    public Integer getFoot_number() {
        return foot_number;
    }

    public void setFoot_number(Integer foot_number) {
        this.foot_number = foot_number;
    }

}
