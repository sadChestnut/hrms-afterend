package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.IProjectRepository;
import com.example.demo.util.ResponseData;
import com.example.demo.vo.Project;

@RestController
@RequestMapping(value = {"/api"})
public class fileController {
	
	@Autowired
	IProjectRepository projectRepository;
	
	String uploadPath="E:/uploadFilePosition/";

	@RequestMapping(value = {"/file/upload"},method = {RequestMethod.POST})
	public ResponseData uploadFile(
			@RequestParam("file") MultipartFile file
			) {
		if (!file.isEmpty()) {
            try {
//            	String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            	String filename = UUID.randomUUID().toString().replaceAll("-", "") + "-" + file.getOriginalFilename();
            	String filePath = uploadPath + filename;
            	BufferedOutputStream out=new BufferedOutputStream(
                        new FileOutputStream(new File(filePath)));
            	out.write(file.getBytes());
            	out.flush();
            	out.close();
            	return ResponseData.success(filename);
            }catch(Exception e) {
            	e.printStackTrace();
            	return ResponseData.failture("文件上传失败:" + e.getMessage());
            }
        } else {
        	return ResponseData.failture("上传文件为空");
        }
	}
	
	@RequestMapping(value = {"/file/download"},method = {RequestMethod.POST})
	public ResponseEntity<FileSystemResource> downloadFile(
			@RequestBody Map<String, Object> params,
			HttpServletRequest request,
			HttpServletResponse response
			)throws IOException {
		String filename =  params.get("filename").toString();
		if (filename != null) {
        	File file = new File(uploadPath + filename);
            if (file.exists()) {

            	response.setContentType("application/octet-stream");
            	response.setHeader("Content-Disposition", "attchement;filename=" + filename);

        		FileInputStream fis = null;
        	    try {
        	        fis = new FileInputStream(file);
        	        byte[] buffer = new byte[128];
        	        int count = 0;
        	        while ((count = fis.read(buffer)) > 0) {
        	        	response.getOutputStream().write(buffer, 0, count);
        	        }
        	    } catch (Exception e) {
        	        e.printStackTrace();
        	    } finally {
        	    	response.getOutputStream().flush();
        	        response.getOutputStream().close();
        	        fis.close();
        	    }
            }
        }
        return null;
	}
}
