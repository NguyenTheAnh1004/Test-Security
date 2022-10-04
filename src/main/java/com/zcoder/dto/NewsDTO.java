package com.zcoder.dto;

import java.util.ArrayList;
import java.util.List;

public class NewsDTO extends AbstractDTO{
	private String title;
	private String thumbnail;
	private String shortDescription;
	private String content;
	private String categoryCode;
	private List<CommentDTO> comment = new ArrayList<CommentDTO>();
	
	
	public NewsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NewsDTO(String title, String thumbnail, String shortDescription, String content, String categoryCode) {
		super();
		this.title = title;
		this.thumbnail = thumbnail;
		this.shortDescription = shortDescription;
		this.content = content;
		this.categoryCode = categoryCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public List<CommentDTO> getComment() {
		return comment;
	}
	public void setComment(List<CommentDTO> comment) {
		this.comment = comment;
	}
	
}
