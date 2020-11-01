package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.example.demo.vo.Employee;
import com.example.demo.vo.employeeEducation;
import com.example.demo.vo.employeeProject;
import com.example.demo.vo.employeeSkill;
import com.example.demo.vo.employeeWork;

public class EmployeeDto {

	private String empnum;

	private String empname;
	
	private String phone;
	
	private String email;
	
	private List<employeeProject> empprojects;
	
	private List<employeeEducation> empeducation;
	
	private List<employeeWork> empwork;
	
	private String identity;
	
	private Employee employee;
	
	private String skill;
	
	private Timestamp createtime;
	
	private Timestamp updatetime;
	
	public EmployeeDto() {}
	
	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
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
	
	public List<employeeProject> getEmpprojects() {
		return this.empprojects;
	}
	
	public void setEmpproject(List<employeeProject> empprojects) {
		this.empprojects = empprojects;
	}
	
	public List<employeeEducation> getEmpeducation() {
		return this.empeducation;
	}
	
	public void setEmpeducation(List<employeeEducation> empeducation) {
		this.empeducation = empeducation;
	}
	
	public List<employeeWork> getEmpwork() {
		return this.empwork;
	}
	
	public void setEmpwork(List<employeeWork> empwork) {
		this.empwork = empwork;
	}
	
	public String getIdentity() {
		return this.identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public Employee getEmployee() {
		return this.employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
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
	
	public EmployeeDto(String id, String name, String identity, String phone, String email, String skill) {
		this.empnum = id;
		this.empname = name;
		this.identity = identity;
		this.phone = phone;
		this.email = email;
		this.skill = skill;
//		this.createtime = createtime;
//		this.updatetime = updatetime;
	}
	
	public EmployeeDto(String id, String name, String identity, String phone, String email, List<employeeEducation> empeducation, List<employeeProject> empprojects, String skill, Timestamp createtime, Timestamp updatetime) {
		this.empnum = id;
		this.empname = name;
		this.identity = identity;
		this.phone = phone;
		this.email = email;
		this.empeducation = empeducation;
		this.empprojects = empprojects;
		this.skill = skill;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}
	
	//针对简历更新
	public EmployeeDto(String id, String name, String identity, String phone, String email, List<employeeEducation> empeducation, String skill, Timestamp createtime, Timestamp updatetime) {
		this.empnum = id;
		this.empname = name;
		this.identity = identity;
		this.phone = phone;
		this.email = email;
		this.empeducation = empeducation;
		this.skill = skill;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}
}
