package com.jitihn.domains;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="chat")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private User sender;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private User receiver;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Ads ads;
	
	@Column(name="message")
	private String message;
	
	@Column(name="messagetime")
	private LocalDateTime messageTime;

	public Chat(String message, LocalDateTime messageTime) {
		super();
		this.message = message;
		this.messageTime = messageTime;
	}

	public Chat() {
	}

	public int getId() {
		return id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Ads getAds() {
		return ads;
	}

	public void setAds(Ads ads) {
		this.ads = ads;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(LocalDateTime messageTime) {
		this.messageTime = messageTime;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", ads=" + ads + ", message="
				+ message + ", messageTime=" + messageTime + "]";
	}
	
	
}
