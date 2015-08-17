package com.yingluo.Appraiser.bean;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.yingluo.Appraiser.http.ResponseNewHome.kinds;

/**
 * 评论实体
 * 
 * @author xy418
 *
 */
public class CommentEntity implements Serializable {

	@SerializedName("user_id")
	public long user_id;

	@SerializedName("to_user_id")
	public long to_user_id;
	@SerializedName("content")
	public String content;
	@SerializedName("to_user_name")
	public String to_user_name;
	@SerializedName("comment_id")
	public long id;
	@SerializedName("insert_time")
	public String insert_time;
	@SerializedName("authName")
	public String authName;
	@SerializedName("authImage")
	public String authImage;
	@SerializedName("authType")
	public int authType;
	@SerializedName("authLevel")
	public int authLevel;

	@SerializedName("index")
	public int index;

	public List<kinds> kinds;
	
	public CommentEntity() {
	}

	public CommentEntity(long user_id, long to_user_id, String content, String to_user_name, long id,
			String insert_time, String authName, String authImage, int authType, int authLevel) {
		super();
		this.user_id = user_id;
		this.to_user_id = to_user_id;
		this.content = content;
		this.to_user_name = to_user_name;
		this.id = id;
		this.insert_time = insert_time;
		this.authName = authName;
		this.authImage = authImage;
		this.authType = authType;
		this.authLevel = authLevel;
	}

	public int getAuthType() {
		return authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	public int getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(int authLevel) {
		this.authLevel = authLevel;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(long to_user_id) {
		this.to_user_id = to_user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTo_user_name() {
		return to_user_name;
	}

	public void setTo_user_name(String to_user_name) {
		this.to_user_name = to_user_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthImage() {
		return authImage;
	}

	public void setAuthImage(String authImage) {
		this.authImage = authImage;
	}

	public List<kinds> getKinds() {
		return kinds;
	}

	public void setKinds(List<kinds> kinds) {
		this.kinds = kinds;
	}

}
