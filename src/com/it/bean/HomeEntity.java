package com.it.bean;

import java.util.ArrayList;
import java.util.List;

public class HomeEntity {

	private List<ChoicesEntity> choices;
	private List<HotsEntity> hots;
	private List<ArticlesEntity> articles;
	private List<AuthorsEntity> authors;

	public HomeEntity() {
		// TODO Auto-generated constructor stub
		choices = new ArrayList<ChoicesEntity>();
		hots = new ArrayList<HotsEntity>();
		articles = new ArrayList<ArticlesEntity>();
		authors = new ArrayList<AuthorsEntity>();
	}

	public void setChoices(List<ChoicesEntity> list) {
		if (list == null)
			return;
		choices.clear();
		choices.addAll(list);
	}

	public void setHots(List<HotsEntity> list) {
		if (list == null)
			return;
		hots.clear();
		hots.addAll(list);
	}

	public void setArticles(List<ArticlesEntity> list) {
		if (list == null)
			return;
		articles.clear();
		articles.addAll(list);
	}

	public void setAuthors(List<AuthorsEntity> list) {
		if (list == null)
			return;
		authors.clear();
		authors.addAll(list);
	}

	public List<ChoicesEntity> getChoices() {
		return choices;
	}

	public List<HotsEntity> getHots() {
		return hots;
	}

	public List<ArticlesEntity> getArticles() {
		return articles;
	}

	public List<AuthorsEntity> getAuthors() {
		return authors;
	}

	@Override
	public String toString() {
		return "HomeEntity [choices=" + choices + ", hots=" + hots
				+ ", articles=" + articles + ", authors=" + authors + "]";
	}

}
