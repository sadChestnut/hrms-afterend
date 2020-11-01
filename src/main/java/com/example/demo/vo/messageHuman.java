package com.example.demo.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message_human")
public class messageHuman {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name = "send")
	private String send;
	
	@Column(name = "sendname")
	private String sendname;
	
	@Column(name = "accept")
	private String accept;
	
	@Column(name = "acceptname")
	private String acceptname;
	
	@Column(name = "empnum")
	private String empnum;
	
	@Column(name = "empname")
	private String empname;
	
	@Column(name = "proid")
	private String proid;
	
	@Column(name = "identity")
	private String identity;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "ischeck")
	private int ischeck;
	
	@Column(name = "senddate")
	private Timestamp senddate;
	
	@Column(name = "reason")
	private String reason;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSend() {
		return this.send;
	}
	
	public void setSend(String send) {
		this.send = send;
	}
	
	public String getSendname() {
		return this.sendname;
	}
	
	public void setSendname(String sendname) {
		this.sendname = sendname;
	}
	
	public String getAccept() {
		return this.accept;
	}
	
	public void setAccept(String accept) {
		this.accept = accept;
	}
	
	public String getAcceptname() {
		return this.acceptname;
	}
	
	public void setAcceptname(String acceptname) {
		this.acceptname = acceptname;
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
	
	public String getProid() {
		return this.proid;
	}
	
	public void setProid(String proid) {
		this.proid = proid;
	}
	
	public String getIdentity() {
		return this.identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getIscheck() {
		return this.ischeck;
	}
	
	public void setIscheck(int ischeck) {
		this.ischeck = ischeck;
	}
	
	public Timestamp getSenddate() {
		return this.senddate;
	}
	
	public void setSenddate(Timestamp senddate) {
		this.senddate = senddate;
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public messageHuman() {}
	
	public messageHuman(String send, String sendname, String accept, String acceptname, String empnum, String empname, String proid, String identity, int status, int ischeck, Timestamp senddate, String reason) {
		this.send = send;
		this.sendname = sendname;
		this.accept = accept;
		this.acceptname = acceptname;
		this.empnum = empnum;
		this.empname = empname;
		this.proid = proid;
		this.identity = identity;
		this.status = status;
		this.ischeck = ischeck;
		this.senddate = senddate;
		this.reason = reason;
	}
}
