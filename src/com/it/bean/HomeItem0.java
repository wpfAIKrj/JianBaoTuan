package com.it.bean;

public class HomeItem0 {
	public String imageUrl;
	public String iconUrl;
	public int grade;
	public String name;
	public int num;

	public HomeItem0(String imageUrl, String iconUrl, int grade, String name,
			int num) {
		super();
		this.imageUrl = imageUrl;
		this.iconUrl = iconUrl;
		this.grade = grade;
		this.name = name;
		this.num = num;
	}

	public HomeItem0(int grade, String name, int num) {
		super();
		this.grade = grade;
		this.name = name;
		this.num = num;
	}

	public HomeItem0() {

	}

}
