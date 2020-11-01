package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.IPositionRepository;
import com.example.demo.dao.IProjectTypeRepository;
import com.example.demo.util.ResponseData;
import com.example.demo.vo.Position;
import com.example.demo.vo.projectType;

@RestController
@RequestMapping(value = {"/api"})
public class knowledgeController {

	@Autowired
	IPositionRepository positionRepository;
	
	@Autowired
	IProjectTypeRepository projectTypeRepository;
	
	@RequestMapping(value = {"/position/add"},method = {RequestMethod.POST})
	public ResponseData addPosition(
			@RequestBody Position posiion
			) {		
		positionRepository.saveAndFlush(posiion);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/position/delete"},method = {RequestMethod.POST})
	public ResponseData delPosition(
			@RequestBody Map<String, Object> params
			) {		
		String positionid = params.get("positionid").toString();
		positionRepository.deleteByPositionid(positionid);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/position/update"},method = {RequestMethod.POST})
	public ResponseData updatePosition(
			@RequestBody Position position
			) {		
		Position p = positionRepository.findByPositionid(position.getPositionid());
		p.setPositionname(position.getPositionname());
		p.setPositiondetail(position.getPositiondetail());
		positionRepository.saveAndFlush(p);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/position/getPositionInfo"},method = {RequestMethod.POST})
	public ResponseData getPositionInfo(
			@RequestBody Map<String, Object> params
			) {		
		String positionid = params.get("positionid").toString();
		Position p = positionRepository.findByPositionid(positionid);
		return ResponseData.success(p);
	}
	
	@RequestMapping(value = {"/position/list"},method = {RequestMethod.POST})
	public ResponseData getPositionList(
			@RequestBody Map<String, Object> params
			) {		
		if (params.get("page").toString() == "" || params.get("count").toString() == "") {
			List<Position> ps = positionRepository.findAll();
			return ResponseData.success(ps);
		} else {
			int page = Integer.parseInt(params.get("page").toString()) - 1;
			int count = Integer.parseInt(params.get("count").toString());
		
			Sort sort =  new Sort(Direction.ASC, "positionid");
			Pageable pageable = new PageRequest(page,count,sort);
		
			Page<Position> positionLists = positionRepository.findAll(pageable);
			return ResponseData.success(positionLists, (int)positionLists.getTotalElements());
		}		
	}
	
	@RequestMapping(value = {"/projectype/add"},method = {RequestMethod.POST})
	public ResponseData addProjectType(
			@RequestBody projectType projectType
			) {		
		projectTypeRepository.saveAndFlush(projectType);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/projectype/delete"},method = {RequestMethod.POST})
	public ResponseData delProjectType(
			@RequestBody Map<String, Object> params
			) {		
		String projecttypeid = params.get("projecttypeid").toString();
		projectTypeRepository.deleteByProjecttypeid(projecttypeid);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/projectype/update"},method = {RequestMethod.POST})
	public ResponseData updateProjectType(
			@RequestBody projectType projecttype
			) {		
		projectType p = projectTypeRepository.findByProjecttypeid(projecttype.getProjecttypeid());
		p.setProjecttypename(projecttype.getProjecttypename());
		p.setProjecttypedetail(projecttype.getProjecttypedetail());
		projectTypeRepository.saveAndFlush(p);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/projectype/getProjectypeInfo"},method = {RequestMethod.POST})
	public ResponseData getProjectTypeInfo(
			@RequestBody Map<String, Object> params
			) {		
		String projecttypeid = params.get("projecttypeid").toString();
		projectType p = projectTypeRepository.findByProjecttypeid(projecttypeid);
		return ResponseData.success(p);
	}
	
	@RequestMapping(value = {"/projectype/list"},method = {RequestMethod.POST})
	public ResponseData getProjectyype(
			@RequestBody Map<String, Object> params
			) {		
		if (params.get("page").toString() == "" || params.get("count").toString() == "") {
			List<projectType> pt = projectTypeRepository.findAll();
			return ResponseData.success(pt);
		} else {
			int page = Integer.parseInt(params.get("page").toString()) - 1;
			int count = Integer.parseInt(params.get("count").toString());
		
			Sort sort =  new Sort(Direction.ASC, "projecttypeid");
			Pageable pageable = new PageRequest(page,count,sort);
		
			Page<projectType> positiontypeLists = projectTypeRepository.findAll(pageable);
			return ResponseData.success(positiontypeLists, (int)positiontypeLists.getTotalElements());
		}		
	}
}
