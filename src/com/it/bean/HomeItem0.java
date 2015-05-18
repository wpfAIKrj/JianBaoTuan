package com.it.bean;

public class HomeItem0 {
	public String image;
	public String authImage;
	public String viewTimes;
	public String name;
	public String linkAddress;

	public HomeItem0(String imageUrl, String iconUrl, String grade,
			String name, String num) {
		super();
		this.image = imageUrl;
		this.authImage = iconUrl;
		this.linkAddress = grade;
		this.name = name;
		this.viewTimes = num;
	}

	public HomeItem0(String grade, String name, String num) {
		super();
		this.linkAddress = grade;
		this.name = name;
		this.viewTimes = num;
	}

	public HomeItem0() {

	}

}
