package com.example.demo.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_type")
public class projectType {
	@Id
	@Column(name = "projecttypeid")
	private String projecttypeid;
	
	@Column(name = "projecttypename")
	private String projecttypename;
	
	@Column(name = "projecttypedetail")
	private String projecttypedetail;
	
	public String getProjecttypeid() {
		return this.projecttypeid;
	}

	public void setProjecttypeid(String projecttypeid) {
		this.projecttypeid = projecttypeid;
	}
	
	public String getProjecttypename() {
		return this.projecttypename;
	}

	public void setProjecttypename(String projecttypename) {
		this.projecttypename = projecttypename;
	}
	
	public String getProjecttypedetail() {
		return this.projecttypedetail;
	}

	public void setProjecttypedetail(String projecttypedetail) {
		this.projecttypedetail = projecttypedetail;
	}
	
	public projectType() {
	}
	
	public projectType(String projecttypeid, String projecttypename, String projecttypedetail) {
		this.projecttypeid = projecttypeid;
		this.projecttypename = projecttypename;
		this.projecttypedetail = projecttypedetail;
	}
}
