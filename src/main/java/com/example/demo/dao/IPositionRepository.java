package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.Position;

public interface IPositionRepository extends JpaRepository<Position, Integer>{

	Position findByPositionid(String positionid);
	
	@Transactional
	public void deleteByPositionid(String positionid);
}
