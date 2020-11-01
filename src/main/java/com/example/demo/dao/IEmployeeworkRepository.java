package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.employeeWork;

public interface IEmployeeworkRepository extends JpaRepository<employeeWork, Integer>{

	List<employeeWork> findByEmpnum(String num);
	
	@Transactional
	public void deleteByEmpnum(String num);
}
