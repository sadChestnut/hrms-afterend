package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.messageHuman;

public interface IMessageHumanRepository extends JpaRepository<messageHuman, Integer>, JpaSpecificationExecutor<messageHuman>{
	
	@Transactional
	messageHuman findByProidAndEmpnum(String proid, String empnum);
	
	@Transactional  
	@Query(value = "SELECT *"
			+ " FROM message_human u WHERE u.send = :send AND u.ischeck = :ischeck AND u.status != 2", nativeQuery = true)
	Page<messageHuman> findBySendAndIscheck(String send, int ischeck, Pageable pageable);
	
	@Transactional
	List<messageHuman> findByProidAndSendAndIscheck(String proid, String send, int ischeck);
	
//	@Transactional
//	messageHuman findByProidAndStatusAndIscheck(String proid, int status, int ischeck);
	
	@Transactional
	Page<messageHuman> findByAccept(String accept, Pageable pageable);
	
	@Transactional
	Page<messageHuman> findBySend(String send, Pageable pageable);
	
	@Transactional
	messageHuman findByProidAndStatus(String proid, int status);
	
	@Transactional
	messageHuman findByProidAndStatusAndEmpnum(String proid, int status, String empnum);
}
