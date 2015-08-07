package com.yingluo.Appraiser.http;

import java.util.List;

public class ResponseSearch extends ResponseRoot{
	
	private Search data;

	public class Search {
		private List<Treasures> treasures;
		private List<Users> users;
		private List<Appraisers> appraisers;
		private List<Articles> articles;
		
		public List<Treasures> getTreasures() {
			return treasures;
		}
		public void setTreasures(List<Treasures> treasures) {
			this.treasures = treasures;
		}
		public List<Users> getUsers() {
			return users;
		}
		public void setUsers(List<Users> users) {
			this.users = users;
		}
		public List<Appraisers> getAppraisers() {
			return appraisers;
		}
		public void setAppraisers(List<Appraisers> appraisers) {
			this.appraisers = appraisers;
		}
		public List<Articles> getArticles() {
			return articles;
		}
		public void setArticles(List<Articles> articles) {
			this.articles = articles;
		}
		
	}
	
	public class Treasures {
		private String id;
		private String treasure_description;
		private String images;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTreasure_description() {
			return treasure_description;
		}
		public void setTreasure_description(String treasure_description) {
			this.treasure_description = treasure_description;
		}
		public String getImages() {
			return images;
		}
		public void setImages(String images) {
			this.images = images;
		}
		
	}
	
	public class Users {
		private String id;
		private String nickname;
		private String portrait;
		private String description;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getPortrait() {
			return portrait;
		}
		public void setPortrait(String portrait) {
			this.portrait = portrait;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	}
	
	public class Appraisers {
		private String id;
		private String nickname;
		private String portrait;
		private String description;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getPortrait() {
			return portrait;
		}
		public void setPortrait(String portrait) {
			this.portrait = portrait;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	}
	
	
	public class Articles{
		private String id;
		private String title;
		private String image;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		
	}


	public Search getData() {
		return data;
	}

	public void setData(Search data) {
		this.data = data;
	}
	
}

