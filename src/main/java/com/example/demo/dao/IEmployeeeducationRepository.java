package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.employeeEducation;

public interface IEmployeeeducationRepository extends JpaRepository<employeeEducation, Integer>{
	
	List<employeeEducation> findByEmpnum(String num);
	
	@Transactional
	public void deleteByEmpnum(String num);
}
