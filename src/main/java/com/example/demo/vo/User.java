package com.example.demo.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_login")
public class User {
	@Id
	@Column(name = "empnum")
	private String empnum;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "identityauth")
	private int identityauth;
	
	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String id) {
		this.empnum = id;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getIdentityauth() {
		return this.identityauth;
	}

	public void setIdentityauth(int identityauth) {
		this.identityauth = identityauth;
	}
	
	public User() {}
	
	public User(String empnum, String password, int identity) {
		this.empnum = empnum;
		this.password = password;
		this.identityauth = identity;
	}
}
