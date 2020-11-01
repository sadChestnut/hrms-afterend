package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.employeeSkill;

public interface IEmployeeskillRepository extends JpaRepository<employeeSkill ,Integer>{

	List<employeeSkill> findByEmpnum(String num);
	
	@Transactional
	String deleteByEmpnum(String num);
	
	List<employeeSkill> findBySkillLike(String skill);
}
