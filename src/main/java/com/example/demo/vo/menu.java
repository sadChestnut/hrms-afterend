package com.example.demo.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class menu {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "auth")
	private String auth;
	
	@Column(name = "label")
	private String label;
	
	@Column(name = "identityid")
	private int identityid;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuth() {
		return this.auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getIdentityid() {
		return this.identityid;
	}

	public void setIdentityid(int identityid) {
		this.identityid = identityid;
	}
	
	public menu() {}
	
	public menu(String auth, String label, int identityid) {
		this.auth = auth;
		this.label = label;
		this.identityid = identityid;
	}
}
