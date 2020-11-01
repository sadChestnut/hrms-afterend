package com.example.demo.vo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "employee_skill")
public class employeeSkill implements Serializable{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "skill")
	private String skill;
	
	@Column(name = "grade")
	private int grade;
	
	@Column(name = "empnum")
	private String empnum;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}
	public employeeSkill() {}
	
	public employeeSkill(String skill, int grade, String empnum) {
		this.skill = skill;
		this.grade = grade;
		this.empnum = empnum;
	}
}
