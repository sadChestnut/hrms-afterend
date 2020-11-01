package com.example.demo.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_work")
public class employeeWork {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "empnum")
	private String empnum;
	
	@Column(name = "companyname")
	private String companyname;
	
	@Column(name = "workstarttime")
	private Timestamp workstarttime;
	
	@Column(name = "workendtime")
	private Timestamp workendtime;
	
	@Column(name = "workdepartment")
	private String workdepartment;
	
	@Column(name = "workpost")
	private String workpost;
	
	@Column(name = "experience")
	private String experience;
	
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
	
	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	
	public Timestamp getWorkstarttime() {
		return this.workstarttime;
	}
	
	public void setWorkstarttime(Timestamp workstarttime) {
		this.workstarttime = workstarttime;
	}
	
	public Timestamp getWorkendtime() {
		return this.workendtime;
	}
	
	public void setWorkendtime(Timestamp workendtime) {
		this.workendtime = workendtime;
	}
	
	public String getWorkdepartment() {
		return this.workdepartment;
	}

	public void setWorkdepartment(String workdepartment) {
		this.workdepartment = workdepartment;
	}
	
	public String getWorkpost() {
		return this.workpost;
	}

	public void setWorkpost(String workpost) {
		this.workpost = workpost;
	}
	
	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public employeeWork () {}
	public employeeWork (String empnum, String companyname, Timestamp workstarttime, Timestamp workendtime, String workdepartment, String workpost, String experience) {
		this.empnum = empnum;
		this.companyname = companyname;
		this.workstarttime = workstarttime;
		this.workendtime = workendtime;
		this.workdepartment = workdepartment;
		this.workpost = workpost;
		this.experience = experience;
	}
}
