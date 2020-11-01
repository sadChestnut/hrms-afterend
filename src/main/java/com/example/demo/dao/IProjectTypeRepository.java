package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.projectType;

public interface IProjectTypeRepository extends JpaRepository<projectType, Integer>{
	
	projectType findByProjecttypeid(String projecttypeid);
	
	@Transactional
	public void deleteByProjecttypeid(String projecttypeid);
}
