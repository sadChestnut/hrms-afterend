package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.employeeProject;

public interface IEmployeeprojectRepository extends JpaRepository<employeeProject,Integer>{

	List<employeeProject> findByEmpnum(String num);

	@Transactional
	String deleteByEmpnum(String num);
	
	@Transactional
	employeeProject findByEmpnumAndProname(String num, String proname);
}
