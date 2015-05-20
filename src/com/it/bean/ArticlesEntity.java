package com.it.bean;

/**
 * @author ytmfdw
 * 文玩杂学实体类
 *
 */
public class ArticlesEntity {

	public String authName;
	public String authImage;
	public String name;
	public String image;
	public String viewTimes;
	public String linkAddress;

	public ArticlesEntity(String authName, String authImage, String name,
			String image, String viewTimes, String linkAddress) {
		super();
		this.authName = authName;
		this.authImage = authImage;
		this.name = name;
		this.image = image;
		this.viewTimes = viewTimes;
		this.linkAddress = linkAddress;
	}

	public ArticlesEntity() {
		super();
	}

	@Override
	public String toString() {
		return "ChoicesEntity [authName=" + authName + ", authImage="
				+ authImage + ", name=" + name + ", image=" + image
				+ ", viewTimes=" + viewTimes + ", linkAddress=" + linkAddress
				+ "]";
	}

}
