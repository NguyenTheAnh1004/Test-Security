package com.zcoder.dto;

public class CommentReplyDTO {
	private String userName;
	private Long commentParentId;
	private String content;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getCommentParentId() {
		return commentParentId;
	}
	public void setCommentParentId(Long commentParentId) {
		this.commentParentId = commentParentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
