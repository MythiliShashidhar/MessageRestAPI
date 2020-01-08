package org.project.Messenger.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Transient;

import org.project.Messenger.model.Comment;


public class MessageDTO {
	
	private long id;
	
	private String message;
	
	private Date created;
	
	private String author;
	
	private Comment comment;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	

}
