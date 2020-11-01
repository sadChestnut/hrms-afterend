package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.projectPosts;

public interface IProjectPostsRepository extends JpaRepository<projectPosts, Integer>{

	List<projectPosts> findByProid(String proid);
	
	@Transactional
	String deleteByProid(String proid);
	
	@Transactional
	@Query(value = "SELECT * FROM projectPosts u WHERE u.proid = :proid AND u.post LIKE CONCAT('%',:post,'%')", nativeQuery=true)
	projectPosts findByProidAndPost(String proid, String post);
}
