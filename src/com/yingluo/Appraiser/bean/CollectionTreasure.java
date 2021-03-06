package com.yingluo.Appraiser.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * 藏品数据
 * 
 * @author xy418
 *
 */
public class CollectionTreasure implements Serializable {

	@SerializedName("authName")
	public String authName;
	@SerializedName("authLevel")
	public int authLevel;
	@SerializedName("user_id")
	public long user_id;
	@SerializedName("authImage")
	public String authImage;
	@SerializedName("name")
	public String name;
	@SerializedName("linkaddress")
	public String linkaddress;

	@SerializedName("viewTimes")
	public long viewTimes;
	@SerializedName("image")
	public String image;
	@SerializedName("images")
	public String[] images;
	@SerializedName("treasure_id")
	public long treasure_id;

	@SerializedName("company")
	public String company;
	@SerializedName("goodAt")
	public String goodAt;

	@SerializedName("authorityData")
	public String authorityData;
	
	public int authType;
	public String[] images1;
	public String[] images2;

	public TreasureType kind;

	@SerializedName("type")
	public int type;

	@SerializedName("status")
	public int status;

	public boolean isCollected;

	@SerializedName("article_id")
	public long article_id;

	@SerializedName("msg")
	public String msg;

	@SerializedName("id")
	public long delete_id;
	@SerializedName("time")
	public long time;

	// 删除是否被选择
	public boolean isSelect = false;

	public CollectionTreasure() {
		super();
	}

	public CollectionTreasure(String authName, int authLevel, long user_id, String authImage, String name,
			String linkaddress, long viewTimes, String image, String[] images, long treasure_id, String company,
			String goodAt,String authorityData) {
		super();
		this.authName = authName;
		this.authLevel = authLevel;
		this.user_id = user_id;
		this.authImage = authImage;
		this.name = name;
		this.linkaddress = linkaddress;
		this.viewTimes = viewTimes;
		this.image = image;
		this.images = images;
		this.treasure_id = treasure_id;
		this.company = company;
		this.goodAt = goodAt;
		this.authorityData = authorityData;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
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

	public String getAuthImage() {
		return authImage;
	}

	public void setAuthImage(String authImage) {
		this.authImage = authImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkaddress() {
		return linkaddress;
	}

	public void setLinkaddress(String linkaddress) {
		this.linkaddress = linkaddress;
	}

	public long getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(long viewTimes) {
		this.viewTimes = viewTimes;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public long getTreasure_id() {
		return treasure_id;
	}

	public void setTreasure_id(long treasure_id) {
		this.treasure_id = treasure_id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getGoodAt() {
		return goodAt;
	}

	public String getAuthorityData() {
		return authorityData;
	}

	public void setAuthorityData(String authorityData) {
		this.authorityData = authorityData;
	}

	public String[] getImages1() {
		return images1;
	}

	public void setImages1(String[] images1) {
		this.images1 = images1;
	}

	public String[] getImages2() {
		return images2;
	}

	public void setImages2(String[] images2) {
		this.images2 = images2;
	}

	public void setGoodAt(String goodAt) {
		this.goodAt = goodAt;
	}

	public int getAuthType() {
		return authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	public TreasureType getKind() {
		return kind;
	}

	public void setKind(TreasureType kind) {
		this.kind = kind;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isCollected() {
		return isCollected;
	}

	public void setCollected(boolean isCollected) {
		this.isCollected = isCollected;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getArticle_id() {
		return article_id;
	}

	public void setArticle_id(long article_id) {
		this.article_id = article_id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
