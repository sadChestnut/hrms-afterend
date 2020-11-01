package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.vo.Employee;
import com.example.demo.vo.User;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee>{

	Employee findByEmpnum(String id);
	
	List<Employee> findByIdentity(String identity);
	
	
	
//	@Transactional
//	@Query(value = "SELECT new com.example.demo.dto.EmployeeDto(u.empnum,u.empname,u.identity,u.phone,u.email,a.skill)"
//			+ "FROM Employee u, employeeSkill a WHERE u.empnum = a.empnum AND u.identity = :identity AND a.skill LIKE CONCAT('%',:skill,'%')")
//	List<EmployeeDto> findEmployeeDto(String identity, String skill);
	
	
//	this.empnum = id;
//	this.empname = name;
//	this.phone = phone;
//	this.email = email;
//	this.identity = identity;
//	this.createtime = createtime;
//	this.updatetime = updatetime;
//	this.skill = skill;
	
	@Transactional
	@Query(value = "SELECT new com.example.demo.dto.EmployeeDto(u.empnum,u.empname,u.identity,u.phone,u.email,u.skill)"
			+ "FROM Employee u WHERE u.identity = :identity AND u.skill LIKE CONCAT('%',:skill,'%')")
	List<Employee> findEmployee(String identity, String skill);

	@Transactional
	@Query(value = "SELECT new com.example.demo.dto.EmployeeDto(u.empnum,u.empname,u.identity,u.phone,u.email,u.skill)"
			+ "FROM Employee u, User a WHERE u.empnum = a.empnum AND a.identityauth BETWEEN 1 AND 2")
	List<Employee> findEmployeeResume();
	
	@Transactional
	public void deleteByEmpnum(String empnum);
	
}
