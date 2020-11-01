package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.Project;

public interface IProjectRepository extends JpaRepository<Project, Integer>, JpaSpecificationExecutor<Project>{
	
	List<Project> findByEmpnum(String empnum);
	
	Project findByProid(String proid);
	
	Page<Project> findByProstatusAndEmpnum(int prostatus, String empnum, Pageable pageable);
	
	@Transactional
	@Query(value = "SELECT * FROM project u WHERE u.prostatus BETWEEN :minprostatus AND :maxprostatus", nativeQuery=true)
	Page<Project> findProject(int minprostatus, int maxprostatus, Pageable pageable);
}
