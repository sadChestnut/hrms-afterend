package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
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
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.IEmployeeRepository;
import com.example.demo.dao.IEmployeeeducationRepository;
import com.example.demo.dao.IEmployeeprojectRepository;
import com.example.demo.dao.IEmployeeskillRepository;
import com.example.demo.dao.IEmployeeworkRepository;
import com.example.demo.util.ResponseData;
import com.example.demo.vo.Employee;
import com.example.demo.vo.User;
import com.example.demo.vo.employeeEducation;
import com.example.demo.vo.employeeProject;
import com.example.demo.vo.employeeSkill;
import com.example.demo.vo.employeeWork;

import com.example.demo.dto.EmployeeDto;

@RestController
@RequestMapping(value = {"/api"})
public class employeeManager {
	
	@Autowired
	IEmployeeRepository employeeRepository;
	
	@Autowired
	IEmployeeprojectRepository employeeprojectRepository;
	
	@Autowired
	IEmployeeskillRepository employeeskillRepository;
	
	@Autowired
	IEmployeeworkRepository employeeworkRepository;
	
	@Autowired
	IEmployeeeducationRepository employeeeducationRepository;
		
	@RequestMapping(value = {"/employee/addInfo"},method = {RequestMethod.POST})
	public ResponseData setEmployeeInfo(
			@RequestBody EmployeeDto employeeDto
			) {
		Employee employee = new Employee(employeeDto.getEmpnum(), employeeDto.getEmpname(), employeeDto.getPhone(),
				employeeDto.getEmail(), employeeDto.getIdentity(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),employeeDto.getSkill());	
		employeeRepository.saveAndFlush(employee);
		
//		List<employeeProject> empproject = employeeDto.getEmpprojects();
//		if (empproject.size() > 0) {
//			employeeProject empp;
//			for(int i=0; i<empproject.size(); i++) {
//				empp = new employeeProject(employeeDto.getEmpnum(), empproject.get(i).getProname(), empproject.get(i).getProdetail(),
//						empproject.get(i).getRole(), empproject.get(i).getRolecontribution());
//				employeeprojectRepository.saveAndFlush(empp);
//			}
//		}
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/employee/getInfo"},method = {RequestMethod.POST})
	public ResponseData getEmployeeInfo(
			@RequestBody Map<String, Object> params
			) {		
		String empnum =  params.get("empnum").toString();
		Employee employee = employeeRepository.findByEmpnum(empnum);
		if(employee == null) {
			return ResponseData.success();
		}
		List<employeeProject> empproject = employeeprojectRepository.findByEmpnum(empnum);
		List<employeeEducation> empeducation = employeeeducationRepository.findByEmpnum(empnum);
		EmployeeDto employeeDto = new EmployeeDto(employee.getEmpnum(), employee.getEmpname(), employee.getIdentity(), employee.getPhone(), employee.getEmail(),
				empeducation, empproject, employee.getSkill(), employee.getCreatetime(), employee.getUpdatetime());
		return ResponseData.success(employeeDto);
	}
	
	@RequestMapping(value = {"/employee/updateInfo"},method = {RequestMethod.POST})
	public ResponseData updateEmployeeInfo(
			@RequestBody EmployeeDto employeeDto
			) {
		Employee employee = employeeRepository.findByEmpnum(employeeDto.getEmpnum());
		employee.setEmpname(employeeDto.getEmpname());
		employee.setIdentity(employeeDto.getIdentity());
		employee.setPhone(employeeDto.getPhone());
		employee.setEmail(employeeDto.getEmail());
		employee.setSkill(employeeDto.getSkill());
		employee.setCreatetime(employee.getUpdatetime());
		employee.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		employeeRepository.saveAndFlush(employee);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/employee/getSearchInfo"},method = {RequestMethod.POST})
	public ResponseData getSearchInfo(
			@RequestBody Map<String, Object> params
			) {
		String post =  params.get("post").toString();
		String skill =  params.get("skill").toString();
		
		Specification<Employee> specification = new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(post)) {
					String wrapSearch = "%" + post + "%";
					predicates.add(cb.like(root.get("identity"), wrapSearch));
				}
				if (!StringUtils.isEmpty(skill)) {
					String wrapSearch = "%" + skill + "%";
					predicates.add(cb.like(root.get("skill"), wrapSearch));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));  
			}
		};
		
		List<Employee> employee = employeeRepository.findAll(specification);
		return ResponseData.success(employee);
	}
	
	@RequestMapping(value = {"/employee/getSingleProDetail"},method = {RequestMethod.POST})
	public ResponseData getSingleProDetail(
			@RequestBody Map<String, Object> params
			) {
		String empnum =  params.get("empnum").toString();
		String proname =  params.get("proname").toString();
		employeeProject empp = employeeprojectRepository.findByEmpnumAndProname(empnum, proname);
		return ResponseData.success(empp);
	}
	
	@RequestMapping(value = {"/employee/updateSingleProDetail"},method = {RequestMethod.POST})
	public ResponseData updateSingleProDetail(
			@RequestBody employeeProject employeeProject
			) {
		employeeProject empp = employeeprojectRepository.findByEmpnumAndProname(employeeProject.getEmpnum(), employeeProject.getProname());
		empp.setProdetail(employeeProject.getProdetail());
		empp.setRole(employeeProject.getRole());
		empp.setRolecontribution(employeeProject.getRolecontribution());
		employeeprojectRepository.saveAndFlush(empp);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/employee/addProListDetail"},method = {RequestMethod.POST})
	public ResponseData addProListDetail(
			@RequestBody List<employeeProject> employeeProject
			) {
		for(int i=0; i<employeeProject.size(); i++) {
			employeeprojectRepository.saveAndFlush(employeeProject.get(i));
		}
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/employee/addWorkListDetail"},method = {RequestMethod.POST})
	public ResponseData addWorkListDetail(
			@RequestBody List<employeeWork> employeeWork
			) {
		for(int i=0; i<employeeWork.size(); i++) {
			employeeworkRepository.saveAndFlush(employeeWork.get(i));
		}
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/employee/addEduListDetail"},method = {RequestMethod.POST})
	public ResponseData addEduListDetail(
			@RequestBody List<employeeEducation> employeeEducation
			) {
		for(int i=0; i<employeeEducation.size(); i++) {
			employeeeducationRepository.saveAndFlush(employeeEducation.get(i));
		}
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/employee/getAllEmployee"},method = {RequestMethod.POST})
	public ResponseData getAllEmployee(
			@RequestBody Map<String, Object> params
			) {
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		String empnum =  params.get("empnum").toString();
		String empname = params.get("empname").toString();
		String identity = params.get("identity").toString();
		
		Sort sort =  new Sort(Direction.ASC, "empnum");
		Pageable pageable = new PageRequest(page,count,sort);
		
		Specification<Employee> specification = new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(empnum)) {
					String wrapSearch = "%" + empnum + "%";
					predicates.add(cb.like(root.get("empnum"), wrapSearch));
				}
				if (!StringUtils.isEmpty(empname)) {
					String wrapSearch = "%" + empname + "%";
					predicates.add(cb.like(root.get("empname"), wrapSearch));
				}
				if (!StringUtils.isEmpty(identity)) {
					String wrapSearch = "%" + identity + "%";
					predicates.add(cb.like(root.get("identity"), wrapSearch));
				}
				Join<Employee,User> joinUser = root.join("user",JoinType.LEFT);
				predicates.add(cb.between(joinUser.get("identityauth"), 1, 2));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		
		Page<Employee> employee = employeeRepository.findAll(specification, pageable);
		return ResponseData.success(employee, (int)employee.getTotalElements());
	}
	
	@RequestMapping(value = {"/employee/getAllWorkList"},method = {RequestMethod.POST})
	public ResponseData getAllWorkList(
			@RequestBody Map<String, Object> params
			) {
		String empnum =  params.get("empnum").toString();
		List<employeeWork> employee = employeeworkRepository.findByEmpnum(empnum);
		return ResponseData.success(employee);
	}
	
	@RequestMapping(value = {"/employee/searchEmployee"},method = {RequestMethod.POST})
	public ResponseData searchEmployee(
			@RequestBody Map<String, Object> params
			) {
		String empnum =  params.get("empnum").toString();
		String empname = params.get("empname").toString();
		String identity = params.get("identity").toString();
		Specification<Employee> specification = new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(empnum)) {
					String wrapSearch = "%" + empnum + "%";
					predicates.add(cb.like(root.get("empnum"), wrapSearch));
				}
				if (!StringUtils.isEmpty(empname)) {
					String wrapSearch = "%" + empname + "%";
					predicates.add(cb.like(root.get("empname"), wrapSearch));
				}
				if (!StringUtils.isEmpty(identity)) {
					String wrapSearch = "%" + identity + "%";
					predicates.add(cb.like(root.get("identity"), wrapSearch));
				}
				Join<Employee,User> joinUser = root.join("user",JoinType.LEFT);
				predicates.add(cb.between(joinUser.get("identityauth"), 1, 2));
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));  
			}
		};
		
		List<Employee> employee = employeeRepository.findAll(specification);
		
		return ResponseData.success(employee);
	}
	
	@RequestMapping(value = {"/employee/delemployee"},method = {RequestMethod.POST})
	public ResponseData delEmployee(
			@RequestBody Map<String, Object> params
			) {
		String empnum =  params.get("empnum").toString();
		if (employeeRepository.findByEmpnum(empnum) != null) {
			employeeRepository.deleteByEmpnum(empnum);
		}
		if (employeeeducationRepository.findByEmpnum(empnum).size() != 0) {
			employeeeducationRepository.deleteByEmpnum(empnum);
		}
		if (employeeworkRepository.findByEmpnum(empnum).size() != 0) {
			employeeworkRepository.deleteByEmpnum(empnum);
		}
		if (employeeprojectRepository.findByEmpnum(empnum).size() != 0) {
			employeeprojectRepository.deleteByEmpnum(empnum);
		}
		return ResponseData.success();
	}
	
}

