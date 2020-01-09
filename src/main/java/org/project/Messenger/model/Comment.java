package org.project.Messenger.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENTS")
public class Comment {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "CommentSequence")
	@SequenceGenerator(name = "CommentSequence", sequenceName = "COMMENT_SEQUENCE")
	private long id;
	
	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "CREATED")
	private Date created;
	
	@Column(name = "AUTHOR")
	private String author;
	
	@Column(name = "MESSAGE_ID", insertable = false, updatable = false )
	private long message_id;
	
	//mention the foreign key here
	//@ManyToOne annotation is associated with Message class variable. @JoinColumn annotation references the mapped column.
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "MESSAGE_ID")
	private Message messageParent ;
	
	public Comment() {	
	}
	
	public Comment(long id, String message, String author, Date created) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = created;
	}
	
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

	public Message getMessageParent() {
		return messageParent;
	}

	public void setMessageParent(Message messageParent) {
		this.messageParent = messageParent;
	}

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	
}
