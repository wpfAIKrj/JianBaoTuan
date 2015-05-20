package com.it.bean;


/**
 * @author ytmfdw
 * 专家实体类
 *
 */
public class AuthorsEntity {

	public String authName;
	public String authImage;
	public String company;
	public String goodAt;

	public AuthorsEntity(String authName, String authImage, String company,
			String goodAt) {
		super();
		this.authName = authName;
		this.authImage = authImage;
		this.company = company;
		this.goodAt = goodAt;
	}

	public AuthorsEntity() {
		super();
	}

	@Override
	public String toString() {
		return "AuthorsEntity [authName=" + authName + ", authImage="
				+ authImage + ", company=" + company + ", goodAt=" + goodAt
				+ "]";
	}

}
