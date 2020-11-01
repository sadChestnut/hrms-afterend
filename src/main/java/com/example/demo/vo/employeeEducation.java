package com.example.demo.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_education")
public class employeeEducation {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "empnum")
	private String empnum;
	
	@Column(name = "starttime")
	private Timestamp starttime;
	
	@Column(name = "endtime")
	private Timestamp endtime;
	
	@Column(name = "schoolname")
	private String schoolname;
	
	@Column(name = "degree")
	private String degree;
	
	@Column(name = "major")
	private String major;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
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
	
	public String getSchoolname() {
		return this.schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public employeeEducation () {}
	
	public employeeEducation (String empnum, Timestamp starttime, Timestamp endtime, String schoolname, String degree, String major) {
		this.empnum = empnum;
		this.starttime = starttime;
		this.endtime = endtime;
		this.schoolname = schoolname;
		this.degree = degree;
		this.major = major;
	}
}
