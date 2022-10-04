package com.zcoder.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
	private String UserName;
	private String fullName;
	private String passWord;
	private String email;
	private List<CommentDTO> comment = new ArrayList<CommentDTO>();
	private List<String> roles = new ArrayList<String>();
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public List<CommentDTO> getComment() {
		return comment;
	}
	public void setComment(List<CommentDTO> comment) {
		this.comment = comment;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
