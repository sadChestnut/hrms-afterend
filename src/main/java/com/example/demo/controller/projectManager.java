package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.IProjectPostsRepository;
import com.example.demo.dao.IProjectRepository;
import com.example.demo.dao.IProjectTimeRepository;
import com.example.demo.dto.ProjectDto;
import com.example.demo.util.ResponseData;
import com.example.demo.vo.Employee;
import com.example.demo.vo.Project;
import com.example.demo.vo.projectPosts;
import com.example.demo.vo.projectTime;

@RestController
@RequestMapping(value = {"/api"})
public class projectManager {
	
	@Autowired
	IProjectRepository projectRepository;
	
	@Autowired
	IProjectPostsRepository projectPostsRepository;
	
	@Autowired
	IProjectTimeRepository projectTimeRepository;
	
	//创建新项目
	@RequestMapping(value = {"/project/addInfo"},method = {RequestMethod.POST})
	public ResponseData setProjectInfo(
			@RequestBody ProjectDto projectDto
			) {
		String proid = UUID.randomUUID().toString().replaceAll("-", "");
		Project project = new Project(proid, projectDto.getProname(), projectDto.getProtype(), projectDto.getProbackground(), projectDto.getPredicttime(),
				projectDto.getCertaintime(), projectDto.getTimelimit(), projectDto.getEmpnum(), 1, projectDto.getFilelists());
		projectRepository.saveAndFlush(project);
		
		List<projectPosts> proposts = projectDto.getPostlists();
		projectPosts posts;
		for(int i=0; i<proposts.size(); i++) {
			posts = new projectPosts(proid, proposts.get(i).getPost(), proposts.get(i).getNumber(), proposts.get(i).getSkill());
			projectPostsRepository.saveAndFlush(posts);
		}
		return ResponseData.success();
	}
	
	//更新项目信息
	@RequestMapping(value = {"/project/saveInfo"},method = {RequestMethod.POST})
	public ResponseData saveProjectInfo(
			@RequestBody ProjectDto projectDto
			) {
		String proid = projectDto.getProid();
		Project project = projectRepository.findByProid(proid);		
		project.setProname(projectDto.getProname());
		project.setProtype(projectDto.getProtype());
		project.setProbackground(projectDto.getProbackground());
		project.setPredicttime(projectDto.getPredicttime());
		project.setCertaintime(projectDto.getCertaintime());
//		project.setSkilllists(projectDto.getSkilllists());
		project.setTimelimit(projectDto.getTimelimit());
		project.setFilelists(projectDto.getFilelists());
		projectRepository.saveAndFlush(project);
		
//		List<projectPosts> proposts = projectDto.getPostlists();
//		projectPosts posts;
//		projectPostsRepository.deleteByProid(proid);
//		for(int i=0; i<proposts.size(); i++) {
//			posts = new projectPosts(proid, proposts.get(i).getPost(), proposts.get(i).getNumber(), proposts.get(i).getSkill());
//			projectPostsRepository.saveAndFlush(posts);
//		}
		return ResponseData.success();
	}
	
	//查询项目经理管理的项目
	@RequestMapping(value = {"/project/getproject"},method = {RequestMethod.POST})
	public ResponseData getProjectList(
			@RequestBody Map<String, Object> params
			) {
		String empnum =  params.get("empnum").toString();
		List<Project> prolist = projectRepository.findByEmpnum(empnum);
		return ResponseData.success(prolist);
	}
	
	//查询某一项目的详情
	@RequestMapping(value = {"/project/getprojectdetail"},method = {RequestMethod.POST})
	public ResponseData getProjectDetail(
			@RequestBody Map<String, Object> params
			) {
		String proid =  params.get("proid").toString();
		Project project = projectRepository.findByProid(proid);
		List<projectPosts> postLists = projectPostsRepository.findByProid(proid);
		ProjectDto projectDto = new ProjectDto(project.getProid(), project.getProname(), project.getProtype(), project.getProbackground(), project.getPredicttime(),
				project.getCertaintime(), project.getTimelimit(), project.getEmpnum(), project.getProstatus(), postLists, project.getFilelists());
		return ResponseData.success(projectDto);
	}
	
	//更新项目状态
	@RequestMapping(value = {"/project/updatestatus"},method = {RequestMethod.POST})
	public ResponseData updateProStatus(
			@RequestBody Map<String, Object> params
			) {
		String proid =  params.get("proid").toString();
		int prostatus = Integer.parseInt(params.get("prostatus").toString());
		Project project = projectRepository.findByProid(proid);
		if (prostatus >= 5) {
			project.setProstatus(5);
		} else {
			project.setProstatus(prostatus + 1);
		}		
		projectRepository.saveAndFlush(project);
		return ResponseData.success();
	}
	
	//查询所有的项目
	@RequestMapping(value = {"/project/getallprojects"},method = {RequestMethod.POST})
	public ResponseData getAllProjects(
			@RequestBody Map<String, Object> params) {
		int prostatus = Integer.parseInt(params.get("prostatus").toString());
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		
		Sort sort =  new Sort(Direction.DESC, "proid");
		Pageable pageable = new PageRequest(page,count,sort);
		
		Specification<Project> specification = new Specification<Project>() {
			@Override
			public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.get("prostatus"), prostatus));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));  
			}
		};
		
		Page<Project> projects = projectRepository.findAll(specification, pageable);
		if (projects == null) return ResponseData.failture();
		List<ProjectDto> projectDto = new ArrayList<>();
		List<projectPosts> proposts;
		
		Iterator<Project> it = projects.iterator();
		while(it.hasNext()) {
			Project p = it.next();
			proposts = projectPostsRepository.findByProid(p.getProid());
			projectDto.add(new ProjectDto(p.getProid(), p.getProname(), p.getProtype(), p.getProbackground(), p.getPredicttime(),
					p.getCertaintime(),p.getTimelimit(), p.getEmpnum(), p.getProstatus(), proposts, p.getFilelists()));
		}
		return ResponseData.success(projectDto, (int)projects.getTotalElements());
	}
	
	//设置员工在不同项目上的工作时间
	@RequestMapping(value = {"/project/setemployeetime"},method = {RequestMethod.POST})
	public ResponseData setEmployeeTime(
			@RequestBody projectTime projecttime
			) {
		projectTime pt = projectTimeRepository.findByProidAndEmpnum(projecttime.getProid(), projecttime.getEmpnum());
		if (pt != null) {
			pt.setStarttime(projecttime.getStarttime());
			pt.setEndtime(projecttime.getEndtime());
			projectTimeRepository.saveAndFlush(pt);
		} else {
			projectTimeRepository.saveAndFlush(projecttime);
		}	
		return ResponseData.success();
	}
	//获取项目配置计划
	@RequestMapping(value = {"/project/getconfigplan"},method = {RequestMethod.POST})
	public ResponseData getConfigPlan(
			@RequestBody Map<String, Object> params
			) {
		String proid =  params.get("proid").toString();
		List<projectTime> pt = projectTimeRepository.findByProid(proid);
		return ResponseData.success(pt);
	}
	//移除配置计划中某一成员
	@RequestMapping(value = {"/project/delemployee"},method = {RequestMethod.POST})
	public ResponseData delEmployee(
			@RequestBody Map<String, Object> params
			) {
		String proid =  params.get("proid").toString();
		String empnum = params.get("empnum").toString();
		projectTimeRepository.deleteByProidAndEmpnum(proid, empnum);
		return ResponseData.success();
	}
	
	//释放配置计划中所有成员
	@RequestMapping(value = {"/project/removeemployees"},method = {RequestMethod.POST})
	public ResponseData removeAllEmployees(
			@RequestBody Map<String, Object> params
			) {
		String proid =  params.get("proid").toString();
		projectTimeRepository.deleteByProid(proid);
		return ResponseData.success();
	}
	
	//查询人员配置完成的项目详情
	@RequestMapping(value = {"/project/getproemployeeplan"},method = {RequestMethod.POST})
	public ResponseData getProEmployeePlan(
			@RequestBody Map<String, Object> params
			) {
		int prostatus = Integer.parseInt(params.get("prostatus").toString());
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		String empnum = params.get("empnum").toString();
		
		Sort sort =  new Sort(Direction.ASC, "proid");
		Pageable pageable = new PageRequest(page,count,sort);
		
		Page<Project> projects = projectRepository.findByProstatusAndEmpnum(prostatus, empnum, pageable);
		if (projects == null) return ResponseData.failture();
		List<ProjectDto> projectDto = new ArrayList<>();;
		List<projectPosts> proposts;
		List<projectTime> pt;
		Iterator<Project> it = projects.iterator();
		while(it.hasNext()) {
			Project p = it.next();
			proposts = projectPostsRepository.findByProid(p.getProid());
			pt = projectTimeRepository.findByProid(p.getProid());
			projectDto.add(new ProjectDto(p.getProid(), p.getProname(), p.getProtype(), p.getProbackground(), p.getPredicttime(),
					p.getCertaintime(),p.getTimelimit(),p.getEmpnum(), p.getProstatus(), proposts, pt));
		}
		return ResponseData.success(projectDto, (int)projects.getTotalElements());
	}
	
	//获取员工工作安排详情
	@RequestMapping(value = {"/project/getempworkdetail"},method = {RequestMethod.POST})
	public ResponseData getEmpWorkDetail(
			@RequestBody Map<String, Object> params
			) {
		String empnum = params.get("empnum").toString();
		List<projectTime> pt = projectTimeRepository.findByEmpnum(empnum);
		if (pt == null) return ResponseData.success();
		return ResponseData.success(pt);
	}
	
	//判断员工在项目时间内是否有空
	@RequestMapping(value = {"/project/getishave"},method = {RequestMethod.POST})
	public ResponseData getIsHave(
			@RequestBody Map<String, Object> params
			) {
		String empnum = params.get("empnum").toString();
		String proid = params.get("proid").toString();
		Project p = projectRepository.findByProid(proid);
		Date certaintime = p.getCertaintime();
		int timelimit = p.getTimelimit();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(certaintime);
	    cal.add(Calendar.MONTH, timelimit);
	    Date endtime = cal.getTime();
		List<projectTime> pt = projectTimeRepository.findByEmpnum(empnum);
		for(int i=0; i<pt.size(); i++) {
			if (certaintime.getTime() > pt.get(i).getEndtime().getTime() || endtime.getTime() <= pt.get(i).getStarttime().getTime()) {
				return ResponseData.success("false");
			}
		}
		return ResponseData.success("true");
	}
	
	//审核项目人员配置计划
	@RequestMapping(value = {"/project/checkemployeeplan"},method = {RequestMethod.POST})
	public ResponseData checkEmployeePlan(
			@RequestBody Map<String, Object> params
			) {
		String proid = params.get("proid").toString();
		List<projectTime> pt = projectTimeRepository.findByProid(proid);
		for(int i=0; i<pt.size(); i++) {
			pt.get(i).setIshascheck(true);
			projectTimeRepository.saveAndFlush(pt.get(i));
		}
		return ResponseData.success();
	}
	
	//审核项目某一具体的人员的配置计划
	@RequestMapping(value = {"/project/checkcertainemployeeplan"},method = {RequestMethod.POST})
	public ResponseData checkCertainEmployeePlan(
			@RequestBody Map<String, Object> params
			) {
		String proid = params.get("proid").toString();
		String empnum = params.get("empnum").toString();
		projectTime pt = projectTimeRepository.findByProidAndEmpnum(proid, empnum);
		pt.setIshascheck(true);
		projectTimeRepository.saveAndFlush(pt);
		return ResponseData.success();
	}
	
	//查询一个状态之间的项目
	@RequestMapping(value = {"/project/getprojectbetweenmaxandmin"},method = {RequestMethod.POST})
	public ResponseData getProjectBetweenMaxandMin(
			@RequestBody Map<String, Object> params
			) {
		int minprostatus = Integer.parseInt(params.get("minprostatus").toString());
		int maxprostatus = Integer.parseInt(params.get("maxprostatus").toString());
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		String proname =  params.get("proname").toString();
		
		Sort sort =  new Sort(Direction.DESC, "proid");
		Pageable pageable = new PageRequest(page,count,sort);
		
		Specification<Project> specification = new Specification<Project>() {
			@Override
			public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(proname)) {
					String wrapSearch = "%" + proname + "%";
					predicates.add(cb.like(root.get("proname"), wrapSearch));
				}
				predicates.add(cb.between(root.get("prostatus"), minprostatus, maxprostatus));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));  
			}
		};
		
		Page<Project> projects = projectRepository.findAll(specification, pageable);
		if (projects == null) return ResponseData.failture();
		List<ProjectDto> projectDto = new ArrayList<>();;
		List<projectPosts> proposts;
		List<projectTime> pt;
		Iterator<Project> it = projects.iterator();
		while(it.hasNext()) {
			Project p = it.next();
			proposts = projectPostsRepository.findByProid(p.getProid());
			pt = projectTimeRepository.findByProid(p.getProid());
			projectDto.add(new ProjectDto(p.getProid(), p.getProname(), p.getProtype(), p.getProbackground(), p.getPredicttime(),
					p.getCertaintime(),p.getTimelimit(),p.getEmpnum(), p.getProstatus(), proposts, pt));
		}
		return ResponseData.success(projectDto, (int)projects.getTotalElements());
	}
	
	//搜索符合条件的项目
	@RequestMapping(value = {"/project/searchProject"},method = {RequestMethod.POST})
	public ResponseData searchProject(
			@RequestBody Map<String, Object> params
			) {
		String empnum =  params.get("empnum").toString();
		String proname =  params.get("proname").toString();
		String protype = params.get("protype").toString();
		String prostatus = params.get("prostatus").toString();
		Boolean showhasdone = Boolean.parseBoolean(params.get("showhasdone").toString());
		
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		
		Specification<Project> specification = new Specification<Project>() {
			@Override
			public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(empnum)) {
					predicates.add(cb.equal(root.get("empnum"), empnum));
				}
				if (!StringUtils.isEmpty(proname)) {
					String wrapSearch = "%" + proname + "%";
					predicates.add(cb.like(root.get("proname"), wrapSearch));
				}
				if (!StringUtils.isEmpty(protype)) {
					String wrapSearch = "%" + protype + "%";
					predicates.add(cb.like(root.get("protype"), wrapSearch));
				}
				if (!StringUtils.isEmpty(prostatus)) {
					predicates.add(cb.equal(root.get("prostatus"), prostatus));
				}
				if (!showhasdone) {
					predicates.add(cb.lt(root.get("prostatus"), 5));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()])); 
			}
		};
		Pageable pageable = new PageRequest(page,count);
		
		Page<Project> project = projectRepository.findAll(specification, pageable);
		return ResponseData.success(project, (int)project.getTotalElements());
	}
	
	@RequestMapping(value = {"/project/updatepropost"},method = {RequestMethod.POST})
	public ResponseData updateProPost(
			@RequestBody List<projectPosts> postlists
			) {
//		projectPostsRepository.deleteByProid(postlists.get(0).getProid());
//		projectPostsRepository.saveAll(postlists);	
		return ResponseData.success();
	}
	
		//搜索符合条件的项目
		@RequestMapping(value = {"/project/searchProjectstatus"},method = {RequestMethod.POST})
		public ResponseData searchProjectstatus(
				@RequestBody Map<String, Object> params
				) {
			String proname =  params.get("proname").toString();
			
			int page = Integer.parseInt(params.get("page").toString()) - 1;
			int count = Integer.parseInt(params.get("count").toString());
			
			Specification<Project> specification = new Specification<Project>() {
				@Override
				public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<>();
					if (!StringUtils.isEmpty(proname)) {
						String wrapSearch = "%" + proname + "%";
						predicates.add(cb.like(root.get("proname"), wrapSearch));
					}					
					predicates.add(cb.between(root.get("prostatus"), 3, 4));
					return cb.and(predicates.toArray(new Predicate[predicates.size()])); 
				}
			};
			Pageable pageable = new PageRequest(page,count);
			
			Page<Project> project = projectRepository.findAll(specification, pageable);
			return ResponseData.success(project, (int)project.getTotalElements());
		}
		
}
