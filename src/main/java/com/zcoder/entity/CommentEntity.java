package com.zcoder.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "news_id")
	private NewsEntity news;
	
	@Column(name = "content")
	private String content;

	@OneToMany(mappedBy = "commentParent")
	private List<CommentReplyEntity> reply = new ArrayList<CommentReplyEntity>();
	
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public NewsEntity getNews() {
		return news;
	}

	public void setNews(NewsEntity news) {
		this.news = news;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<CommentReplyEntity> getReply() {
		return reply;
	}

	public void setReply(List<CommentReplyEntity> reply) {
		this.reply = reply;
	}
	
	
}
