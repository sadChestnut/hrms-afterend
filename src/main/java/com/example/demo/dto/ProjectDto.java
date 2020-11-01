package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.example.demo.vo.projectPosts;
import com.example.demo.vo.projectTime;

public class ProjectDto {
	
	private String proid;
	private String proname;
	private String protype;
	private String probackground;
	private Timestamp predicttime;
	private Timestamp certaintime;
	private int timelimit;
//	private String skilllists;
	private String empnum;
	private int prostatus;
	private List<projectPosts> postlists;
	private List<projectTime> proemployeeplan;
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
	
	public int getProstatus() {
		return this.prostatus;
	}
	
	public void setProstatus(int prostatus) {
		this.prostatus = prostatus;
	}
	
	public List<projectPosts> getPostlists() {
		return this.postlists;
	}
	
	public void setPostlists(List<projectPosts> postlists) {
		this.postlists = postlists;
	}
	
	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}
	
	public List<projectTime> getProemployeeplan() {
		return this.proemployeeplan;
	}
	
	public void setProemployeeplan(List<projectTime> proemployeeplan) {
		this.proemployeeplan = proemployeeplan;
	}
	
	public String getFilelists() {
		return this.filelists;
	}
	
	public void setFilelists(String filelists) {
		this.filelists = filelists;
	}
	
	public ProjectDto() {}
	
	public ProjectDto(String proid, String proname, String protype, String probackground, Timestamp predicttime, Timestamp certaintime, int timelimit, String empnum, List<projectPosts> postlists) {
		this.proid = proid;
		this.proname = proname;
		this.protype = protype;
		this.probackground = probackground;
		this.predicttime = predicttime;
		this.certaintime = certaintime;
		this.timelimit = timelimit;
//		this.skilllists = skilllists;
		this.empnum = empnum;
		this.postlists = postlists;
	}
	
	public ProjectDto(String proid, String proname, String protype, String probackground, Timestamp predicttime, Timestamp certaintime, int timelimit, String empnum, List<projectPosts> postlists, String filelists) {
		this.proid = proid;
		this.proname = proname;
		this.protype = protype;
		this.probackground = probackground;
		this.predicttime = predicttime;
		this.certaintime = certaintime;
		this.timelimit = timelimit;
//		this.skilllists = skilllists;
		this.empnum = empnum;
		this.postlists = postlists;
		this.filelists = filelists;
	}
	
	public ProjectDto(String proid, String proname, String protype, String probackground, Timestamp predicttime, Timestamp certaintime, int timelimit, String empnum, int prostatus, List<projectPosts> postlists, String filelists) {
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
		this.postlists = postlists;
		this.filelists = filelists;
	}
	public ProjectDto(String proid, String proname, String protype, String probackground, Timestamp predicttime, Timestamp certaintime, int timelimit, String empnum, int prostatus, List<projectPosts> postlists, List<projectTime> proemployeeplan) {
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
		this.postlists = postlists;
		this.proemployeeplan = proemployeeplan;
	}
}
