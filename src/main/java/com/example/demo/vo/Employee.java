package com.example.demo.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee implements Serializable{
	@Id
	@Column(name = "empnum")
	private String empnum;
	@Column(name = "empname")
	private String empname;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email")
	private String email;
	@Column(name = "identity")
	private String identity;
	@Column(name = "createtime")
	private Timestamp createtime;
	@Column(name = "updatetime")
	private Timestamp updatetime;
	@Column(name = "skill")
	private String skill;
	
	@OneToOne
    @JoinColumn(name="empnum")
	private User user;
	
	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String id) {
		this.empnum = id;
	}
	
	public String getEmpname() {
		return this.empname;
	}
	
	public void setEmpname(String name) {
		this.empname = name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getIdentity() {
		return this.identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public Timestamp getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
	public Timestamp getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getSkill() {
		return this.skill;
	}
	
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public Employee() {
	}
	
	public Employee(String id, String name, String phone, String email, String identity, Timestamp createtime, Timestamp updatetime, String skill) {
		this.empnum = id;
		this.empname = name;
		this.phone = phone;
		this.email = email;
		this.identity = identity;
		this.createtime = createtime;
		this.updatetime = updatetime;
		this.skill = skill;
	}
}
