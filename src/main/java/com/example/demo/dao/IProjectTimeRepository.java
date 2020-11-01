package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.projectTime;

public interface IProjectTimeRepository extends JpaRepository<projectTime, Integer>{

	@Transactional
	projectTime findByProidAndEmpnum(String proid, String empnum);
	
	@Transactional
	List<projectTime> findByProid(String proid);
	
	@Transactional
	String deleteByProidAndEmpnum(String proid, String empnum);
	
	@Transactional
	String deleteByProid(String proid);
	
	@Transactional
	List<projectTime> findByEmpnum(String empnum);
}
