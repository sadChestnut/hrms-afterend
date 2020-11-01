package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.menu;

public interface IMenuRepository extends JpaRepository<menu, Integer>{

	List<menu> findByIdentityid (int identityid);
}
