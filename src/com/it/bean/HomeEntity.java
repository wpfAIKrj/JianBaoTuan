package com.it.bean;

import java.util.ArrayList;
import java.util.List;

public class HomeEntity {

	private List<CollectionEntity> advertising;
	private List<CollectionEntity> choices;
	private List<CollectionEntity> hots;
	private List<CollectionEntity> articles;
	private List<CollectionEntity> authors;

	public HomeEntity() {
		// TODO Auto-generated constructor stub
		advertising = new ArrayList<CollectionEntity>();
		choices = new ArrayList<CollectionEntity>();
		hots = new ArrayList<CollectionEntity>();
		articles = new ArrayList<CollectionEntity>();
		authors = new ArrayList<CollectionEntity>();
	}

	public List<CollectionEntity> getAdvertising() {
		return advertising;
	}

	public void setAdvertising(List<CollectionEntity> advertising) {
		this.advertising = advertising;
	}

	public void setChoices(List<CollectionEntity> list) {
		if (list == null)
			return;
		choices.clear();
		choices.addAll(list);
	}

	public void setHots(List<CollectionEntity> list) {
		if (list == null)
			return;
		hots.clear();
		hots.addAll(list);
	}

	public void setArticles(List<CollectionEntity> list) {
		if (list == null)
			return;
		articles.clear();
		articles.addAll(list);
	}

	public void setAuthors(List<CollectionEntity> list) {
		if (list == null)
			return;
		authors.clear();
		authors.addAll(list);
	}

	public List<CollectionEntity> getChoices() {
		return choices;
	}

	public List<CollectionEntity> getHots() {
		return hots;
	}

	public List<CollectionEntity> getArticles() {
		return articles;
	}

	public List<CollectionEntity> getAuthors() {
		return authors;
	}

	@Override
	public String toString() {
		return "HomeEntity [choices=" + choices + ", hots=" + hots
				+ ", articles=" + articles + ", authors=" + authors + "]";
	}

}
