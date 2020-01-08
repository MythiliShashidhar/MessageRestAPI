package org.project.Messenger.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MESSAGE")
@XmlRootElement
public class Message {
	
	@Id
    @Column(name = "MESSAGE_ID")
	@GeneratedValue(generator="MessageSequence") 
	@SequenceGenerator(name="MessageSequence",sequenceName="message_sequence") 
	private long id;
	
	@Column(name="MESSAGE")
	private String message;
	
	@Column(name="CREATED")
	private Date created;
	
	@Column(name="AUTHOR")
	private String author;
	
	//@OneToMany annotation is used to define the property in Comment class that will be used to map the mappedBy variable
	//@OneToMany
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="messageParent", fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<Comment>();
	
	public Message() {	
	}
	
	public Message(long id, String message, String author, Date created) {
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

    //comment data not shown when message being pulled up
	//@XmlTransient
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
