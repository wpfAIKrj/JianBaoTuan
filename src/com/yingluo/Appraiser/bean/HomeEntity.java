package com.yingluo.Appraiser.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ytmfdw,主页实体类，解析服务器返回的数据，包装成一个类
 *
 */
public class HomeEntity implements Serializable{

	private List<CollectionTreasure> advertising;
	private List<CollectionTreasure> choices;
	private List<CollectionTreasure> hots;
	private List<ContentInfo> articles;
	private List<CollectionTreasure> authors;

	public HomeEntity() {
		// TODO Auto-generated constructor stub
		advertising = new ArrayList<CollectionTreasure>();
		choices = new ArrayList<CollectionTreasure>();
		hots = new ArrayList<CollectionTreasure>();
		articles = new ArrayList<ContentInfo>();
		authors = new ArrayList<CollectionTreasure>();
	}

	public List<CollectionTreasure> getAdvertising() {
		return advertising;
	}

	public void setAdvertising(List<CollectionTreasure> advertising) {
		this.advertising = advertising;
	}

	public void setChoices(List<CollectionTreasure> list) {
		if (list == null)
			return;
		choices.clear();
		choices.addAll(list);
	}

	public void setHots(List<CollectionTreasure> list) {
		if (list == null)
			return;
		hots.clear();
		hots.addAll(list);
	}

	public void setArticles(List<ContentInfo> list) {
		if (list == null)
			return;
		articles.clear();
		articles.addAll(list);
	}

	public void setAuthors(List<CollectionTreasure> list) {
		if (list == null)
			return;
		authors.clear();
		authors.addAll(list);
	}

	public List<CollectionTreasure> getChoices() {
		return choices;
	}

	public List<CollectionTreasure> getHots() {
		return hots;
	}

	public List<ContentInfo> getArticles() {
		return articles;
	}

	public List<CollectionTreasure> getAuthors() {
		return authors;
	}

	@Override
	public String toString() {
		return "HomeEntity [choices=" + choices + ", hots=" + hots
				+ ", articles=" + articles + ", authors=" + authors + "]";
	}

}
