package com.it.bean;


/**
 * @author ytmfdw
 * 主页实体类
 *
 */
public class CollectionEntity {
	public String authName;
	public int authLevel;
	public String authImage;
	public String name;
	public String image;
	public String[] images;
	public long treasure_id;
	public long article_id;
	public String msg;
	public long viewTimes;
	public String company;
	public String goodAt;
	public long author_id;
	public String linkaddress;

	public CollectionEntity() {
		// TODO Auto-generated constructor stub
	}

	public CollectionEntity(String authName, int authLevel,
			String authImage, String name, String image, String[] images,
			long treasure_id, long article_id, String msg, long viewTimes,
			String company, String goodAt, long author_id, String linkaddress) {
		super();
		this.authName = authName;
		this.authLevel = authLevel;
		this.authImage = authImage;
		this.name = name;
		this.image = image;
		this.images = images;
		this.treasure_id = treasure_id;
		this.article_id = article_id;
		this.msg = msg;
		this.viewTimes = viewTimes;
		this.company = company;
		this.goodAt = goodAt;
		this.author_id = author_id;
		this.linkaddress = linkaddress;
	}

	// advertising
	public CollectionEntity(String image, long treasure_id, String linkaddress) {
		super();
		this.image = image;
		this.treasure_id = treasure_id;
		this.linkaddress = linkaddress;
	}

	// choices //hots
	public CollectionEntity(String authName, int authLevel,
			String authImage, String name, String image, long treasure_id,
			long viewTimes) {
		super();
		this.authName = authName;
		this.authLevel = authLevel;
		this.authImage = authImage;
		this.name = name;
		this.image = image;
		this.treasure_id = treasure_id;
		this.viewTimes = viewTimes;
	}

	// articles
	public CollectionEntity(String image, long article_id, String msg,
			long viewTimes) {
		super();
		this.image = image;
		this.article_id = article_id;
		this.msg = msg;
		this.viewTimes = viewTimes;
	}

	// authors
	public CollectionEntity(String authName, String authImage, String company,
			String goodAt, long author_id) {
		super();
		this.authName = authName;
		this.authImage = authImage;
		this.company = company;
		this.goodAt = goodAt;
		this.author_id = author_id;
	}

	@Override
	public String toString() {
		return "CollectionEntity [authName=" + authName + ", authLevel="
				+ authLevel + ", authImage=" + authImage + ", name=" + name
				+ ", image=" + image + ", images=" + images + ", treasure_id="
				+ treasure_id + ", article_id=" + article_id + ", msg=" + msg
				+ ", viewTimes=" + viewTimes + ", company=" + company
				+ ", goodAt=" + goodAt + ", author_id=" + author_id
				+ ", linkaddress=" + linkaddress + "]";
	}

}
