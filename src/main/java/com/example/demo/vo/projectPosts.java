package com.example.demo.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_posts")
public class projectPosts {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "proid")
	private String proid;
	
	@Column(name = "post")
	private String post;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "skill")
	private String skill;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getProid() {
		return this.proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}
	
	public String getPost() {
		return this.post;
	}
	
	public void setPost(String post) {
		this.post = post;
	}
	
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public projectPosts() {}
	
	public projectPosts(String proid, String post, int number,String skill) {
		this.proid = proid;
		this.post = post;
		this.number = number;
		this.skill = skill;
	}
}
