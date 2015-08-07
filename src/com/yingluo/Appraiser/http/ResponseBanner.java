package com.yingluo.Appraiser.http;

import java.util.List;

public class ResponseBanner extends ResponseRoot{
	
	private List<Banner> data;

	public class Banner {
		private String id;
		private String title;
		private String image;
		private String address;
		private String treasure_id;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTreasure_id() {
			return treasure_id;
		}
		public void setTreasure_id(String treasure_id) {
			this.treasure_id = treasure_id;
		}
		
	}

	public List<Banner> getData() {
		return data;
	}

	public void setData(List<Banner> data) {
		this.data = data;
	}
	
	
}

