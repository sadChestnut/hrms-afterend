package com.example.demo.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_time")
public class projectTime {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "proid")
	private String proid;
	
	@Column(name = "empnum")
	private String empnum;
	
	@Column(name = "empname")
	private String empname;
	
	@Column(name = "starttime")
	private Timestamp starttime;
	
	@Column(name = "endtime")
	private Timestamp endtime;
	
	@Column(name = "identity")
	private String identity;
	
	@Column(name = "ishascheck")
	private Boolean ishascheck;
	
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
	
	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}
	
	public String getEmpname() {
		return this.empname;
	}
	
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	public Timestamp getStarttime() {
		return this.starttime;
	}
	
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	
	public Timestamp getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	
	public String getIdentity() {
		return this.identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public Boolean getIshascheck() {
		return this.ishascheck;
	}
	
	public void setIshascheck(Boolean ishascheck) {
		this.ishascheck = ishascheck;
	}
	
	public projectTime() {}
	
	public projectTime(String proid, String empnum, String empname, Timestamp starttime, Timestamp endtime, String identity, Boolean ishascheck) {
		this.proid = proid;
		this.empnum = empnum;
		this.empname = empname;
		this.starttime = starttime;
		this.endtime = endtime;
		this.identity = identity;
		this.ishascheck = ishascheck;
	}
}
