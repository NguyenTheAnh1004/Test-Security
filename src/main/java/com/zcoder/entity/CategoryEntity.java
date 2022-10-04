package com.zcoder.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")

public class CategoryEntity extends BaseEntity {
	
	@Column(name = "name", unique = true)
	private String name;

	@OneToMany(mappedBy = "category")
	private List<NewsEntity> news = new ArrayList<NewsEntity>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NewsEntity> getNews() {
		return news;
	}

	public void setNews(List<NewsEntity> news) {
		this.news = news;
	}
	
}
