package com.jitihn.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ads")
public class Ads {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	private User user;
	
	@Column(name= "add_title")
	private String add_title;
	
	@Column(name= "add_description", length = 500)
	private String add_description;

	public Ads() {
	}

	public Ads(int id, User user, String add_title, String add_description) {
		super();
		this.id = id;
		this.user = user;
		this.add_title = add_title;
		this.add_description = add_description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAdd_title() {
		return add_title;
	}

	public void setAdd_title(String add_title) {
		this.add_title = add_title;
	}

	public String getAdd_description() {
		return add_description;
	}

	public void setAdd_description(String add_description) {
		this.add_description = add_description;
	}

	@Override
	public String toString() {
		return "Ads [id=" + id + ", user=" + user + ", add_title=" + add_title + ", add_description=" + add_description
				+ "]";
	}	
}
