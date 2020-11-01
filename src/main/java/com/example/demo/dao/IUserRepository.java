package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.User;

public interface IUserRepository extends JpaRepository<User, Integer>{
	User findByEmpnum(String empnum);
	
	@Transactional
	@Query(value = "SELECT new com.example.demo.vo.User(u.empnum,u.password,u.identityauth)"
			+ "FROM User u WHERE u.identityauth = :auth ORDER By u.empnum desc")
	List<User> findByIdentityauth(int auth);
	
	@Transactional
	public void deleteByEmpnum(String empnum);
}
