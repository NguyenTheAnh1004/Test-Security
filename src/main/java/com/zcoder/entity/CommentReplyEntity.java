package com.zcoder.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commentReply")
public class CommentReplyEntity extends BaseEntity{
	@JoinColumn(name = "content")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "commentparent_id")
	private CommentEntity commentParent;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentEntity getCommentParent() {
		return commentParent;
	}

	public void setCommentParent(CommentEntity commentParent) {
		this.commentParent = commentParent;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	   
}
