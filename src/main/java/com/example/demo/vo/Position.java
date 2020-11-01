package com.example.demo.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position {
	
	@Id
	@Column(name = "positionid")
	private String positionid;
	
	@Column(name = "positionname")
	private String positionname;
	
	@Column(name = "positiondetail")
	private String positiondetail;
	
	public String getPositionid() {
		return this.positionid;
	}

	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}
	
	public String getPositionname() {
		return this.positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}
	
	public String getPositiondetail() {
		return this.positiondetail;
	}

	public void setPositiondetail(String positiondetail) {
		this.positiondetail = positiondetail;
	}
	
	public Position() {
	}
	
	public Position(String positionid, String positionname, String positiondetail) {
		this.positionid = positionid;
		this.positionname = positionname;
		this.positiondetail = positiondetail;
	}
}
