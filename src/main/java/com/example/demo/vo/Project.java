package com.example.demo.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@Column(name = "proid")
	private String proid;
	
	@Column(name = "proname")
	private String proname;
	
	@Column(name = "protype")
	private String protype;
	
	@Column(name = "probackground")
	private String probackground;
	
	@Column(name = "predicttime")
	private Timestamp predicttime;
	
	@Column(name = "certaintime")
	private Timestamp certaintime;
	
	@Column(name = "timelimit")
	private int timelimit;
	
//	@Column(name = "skilllists")
//	private String skilllists;
	
	@Column(name = "empnum")
	private String empnum;
	
	@Column(name = "prostatus")
	private int prostatus;
	
	@Column(name = "filelists")
	private String filelists;
	
	public String getProid() {
		return this.proid;
	}
	
	public void setProid(String proid) {
		this.proid = proid;
	}
	
	public String getProname() {
		return this.proname;
	}
	
	public void setProname(String proname) {
		this.proname = proname;
	}
	
	public String getProtype() {
		return this.protype;
	}
	
	public void setProtype(String protype) {
		this.protype = protype;
	}
	
	public String getProbackground() {
		return this.probackground;
	}
	
	public void setProbackground(String probackground) {
		this.probackground = probackground;
	}
	
	public Timestamp getPredicttime() {
		return this.predicttime;
	}
	
	public void setPredicttime(Timestamp predicttime) {
		this.predicttime = predicttime;
	}
	
	public Timestamp getCertaintime() {
		return this.certaintime;
	}
	
	public void setCertaintime(Timestamp certaintime) {
		this.certaintime = certaintime;
	}
	
	public int getTimelimit() {
		return this.timelimit;
	}
	
	public void setTimelimit(int timelimit) {
		this.timelimit = timelimit;
	}
	
	
//	public String getSkilllists() {
//		return this.skilllists;
//	}
//	
//	public void setSkilllists(String skilllists) {
//		this.skilllists = skilllists;
//	}
	
	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}
	
	public int getProstatus() {
		return this.prostatus;
	}
	
	public void setProstatus(int prostatus) {
		this.prostatus = prostatus;
	}
	
	public String getFilelists() {
		return this.filelists;
	}
	
	public void setFilelists(String filelists) {
		this.filelists = filelists;
	}
	
	public Project() {}
	
	public Project(String proid, String proname, String protype, String probackground, Timestamp predicttime, Timestamp certaintime, int timelimit, String empnum, int prostatus, String filelists) {
		this.proid = proid;
		this.proname = proname;
		this.protype = protype;
		this.probackground = probackground;
		this.predicttime = predicttime;
		this.certaintime = certaintime;
		this.timelimit = timelimit;
//		this.skilllists = skilllists;
		this.empnum = empnum;
		this.prostatus = prostatus;
		this.filelists = filelists;
	}
}
