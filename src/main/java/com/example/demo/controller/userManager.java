package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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

import net.sf.json.JSONObject;

import com.example.demo.dao.IEmployeeRepository;
import com.example.demo.dao.IMenuRepository;
import com.example.demo.dao.IUserRepository;
import com.example.demo.dto.EmployeeAuthDto;
import com.example.demo.util.ResponseData;
import com.example.demo.util.ValiPhoto;
import com.example.demo.vo.Employee;
import com.example.demo.vo.User;
import com.example.demo.vo.menu;

@RestController
@RequestMapping(value = {"/api"})
public class userManager {

	int identityid = 1;
	
	String valiCode;
	
	Boolean hasLogined = false;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IMenuRepository menuRepository;
	
	@Autowired
	IEmployeeRepository employeeRepository;
	
	@RequestMapping(value = {"/login"},method = {RequestMethod.POST})
	public ResponseData login(
			@RequestBody Map<String, Object> params
			) {
		User getUser = userRepository.findByEmpnum(params.get("empnum").toString());
		String valicode =  params.get("valicode").toString();
		hasLogined = false;
		List<menu> menulists;
		if (getUser == null ) {
			return ResponseData.failture("用户不存在");
		} else {
			if(getUser.getPassword().compareTo(params.get("password").toString()) != 0) {
				return ResponseData.failture("密码错误");
			} else if (valicode.length() > 0 && valicode.toLowerCase().compareTo(valiCode.toLowerCase()) != 0) {
				return ResponseData.failture("验证码错误");
			} else {
				identityid = getUser.getIdentityauth();
				hasLogined = true;
				menulists = menuRepository.findByIdentityid(identityid);
			}		
		}
		return ResponseData.success(menulists);
	}
	
	@RequestMapping(value = {"/logout"},method = {RequestMethod.POST})
	public ResponseData logout() {
		hasLogined = false;
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/getauths"},method = {RequestMethod.POST})
	public ResponseData getAuths(
			@RequestBody Map<String, Object> params
			) {
		User getUser = userRepository.findByEmpnum(params.get("empnum").toString());
		List<menu> menulists = menuRepository.findByIdentityid(getUser.getIdentityauth());
		if (hasLogined) {
			return ResponseData.success(menulists);
		} else {
			return ResponseData.failture();
		}
	}
	
	@RequestMapping(value = {"/validateCode"})
	public Object StringvalidateCode(HttpServletResponse response) throws Exception{
	    response.setContentType("image/jpeg");
	    response.setHeader("Pragma",  "no-cache");
	    response.setHeader("Cache-Control",  "no-cache");
	    response.setDateHeader("Expires",  0);
	    ValiPhoto vCode =new ValiPhoto(120, 40, 5, 100);
	    valiCode = vCode.getValicode();
	    vCode.write(response.getOutputStream());
	    return null;
	}

//	https://blog.csdn.net/linzhiqiang0316/article/details/52639265
	
	@RequestMapping(value = {"/getempinfoandauth"},method = {RequestMethod.POST})
	public ResponseData getEmpInfoAndAuth(
			@RequestBody Map<String, Object> params
			) {
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		
		Sort sort =  new Sort(Direction.ASC, "empnum");
		Pageable pageable = new PageRequest(page,count,sort);
		Page<Employee> emps = employeeRepository.findAll(pageable);
		Iterator<Employee> it = emps.iterator();
		List<EmployeeAuthDto> employeeAuth = new ArrayList<>();
		while(it.hasNext()) {
			Employee e = it.next();
			User getUser = userRepository.findByEmpnum(e.getEmpnum());
			employeeAuth.add(new EmployeeAuthDto(e.getEmpnum(), e.getEmpname(), e.getIdentity(), e.getPhone(), e.getEmail(), getUser.getIdentityauth()));
		}
		return ResponseData.success(employeeAuth, (int)emps.getTotalElements());
	}
	
	@RequestMapping(value = {"/getauthmenus"},method = {RequestMethod.POST})
	public ResponseData getAuthMenus(@RequestBody Map<String, Object> params) {
		int auth = Integer.parseInt(params.get("auth").toString());
		List<menu> menulists = menuRepository.findByIdentityid(auth);;
		return ResponseData.success(menulists);
	}
	
	@RequestMapping(value = {"/changeempauth"},method = {RequestMethod.POST})
	public ResponseData changeEmpAuth(@RequestBody Map<String, Object> params) {
		int auth = Integer.parseInt(params.get("auth").toString());
		String empnum = params.get("empnum").toString();
		String identity = params.get("identity").toString();
		User getUser = userRepository.findByEmpnum(empnum);
		getUser.setIdentityauth(auth);
		userRepository.saveAndFlush(getUser);
		Employee emp = employeeRepository.findByEmpnum(empnum);
		emp.setIdentity(identity);
		employeeRepository.saveAndFlush(emp);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/modifyPassword"},method = {RequestMethod.POST})
	public ResponseData modifyPassword(@RequestBody Map<String, Object> params) {
		String newpassword = params.get("newPassword").toString();
		String oldpassword = params.get("oldPassword").toString();
		String empnum = params.get("empnum").toString();
		User getUser = userRepository.findByEmpnum(empnum);
		if (getUser.getPassword().compareTo(oldpassword) == 0) {
			getUser.setPassword(newpassword);
			userRepository.saveAndFlush(getUser);
			return ResponseData.success();
		} else {
			return ResponseData.failture("密码二次校验错误");
		}
	}
	
	@RequestMapping(value = {"/adminModifyPassword"},method = {RequestMethod.POST})
	public ResponseData adminModifyPassword(@RequestBody Map<String, Object> params) {
		String adminpassword = params.get("adminpassword").toString();
		String userpassword = params.get("userpassword").toString();
		String empnum = params.get("empnum").toString();
		String adminnum = params.get("admin").toString();
		User admin = userRepository.findByEmpnum(adminnum);
		User user = userRepository.findByEmpnum(empnum);
		if (admin.getPassword().compareTo(adminpassword) == 0) {
			user.setPassword(userpassword);
			userRepository.saveAndFlush(user);
			return ResponseData.success();
		} else {
			return ResponseData.failture("管理员密码二次校验错误");
		}
	}
	
	@RequestMapping(value = {"/getEmpnum"},method = {RequestMethod.POST})
	public ResponseData getEmpnum(@RequestBody Map<String, Object> params) {
		int identityauth = Integer.parseInt(params.get("identityauth").toString());
		List<User> userList = userRepository.findByIdentityauth(identityauth);
		return ResponseData.success(userList);
	}
	
	@RequestMapping(value = {"/addUser"},method = {RequestMethod.POST})
	public ResponseData addUser(@RequestBody Map<String, Object> params) {
		String empnum = params.get("empnum").toString();
		int identityauth = Integer.parseInt(params.get("identityauth").toString());
		User user = new User(empnum, "1234567", identityauth);
		userRepository.saveAndFlush(user);
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/removeUser"},method = {RequestMethod.POST})
	public ResponseData removeUser(@RequestBody Map<String, Object> params) {
		String empnum = params.get("empnum").toString();
		userRepository.deleteByEmpnum(empnum);
		return ResponseData.success();
	}
}
