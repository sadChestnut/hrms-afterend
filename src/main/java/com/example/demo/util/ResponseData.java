package com.example.demo.util;
import java.util.List;

public class ResponseData {

	private static final String SUCCESS = "0";
	private static final String FAILTURE = "-1";
	private Object obj = null;
	private String msg;
	private String code = "返回信息";
	
	public static class Data{
		private Object list;
		private int total;
		
		public Data() {
			
		}
		public Data(int total,Object list) {
			this.total = total;
			this.list = list;
		}
		
		public Object getList() {
			return list;
		}
		public void setList(Object list) {
			this.list = list;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
	}
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	private ResponseData(String msg, Object obj) {
		this.obj = obj;
		this.msg = msg;
	}

	private ResponseData(String msg, String code) {
		this.msg = msg;
		this.code = code;
	}
	
	private ResponseData(String msg) {
		this.msg = msg;
	}
	
	public static ResponseData success() {
		ResponseData responseData = new ResponseData(SUCCESS);
		return responseData;
	}
	
	public static ResponseData failture() {
		ResponseData responseData = new ResponseData(FAILTURE);
		return responseData;
	}
	
	public static ResponseData success(Object obj) {
		ResponseData responseData = null;
		if(obj instanceof List) {
			responseData = new ResponseData(SUCCESS,new Data(((List<?>)obj).size(),obj));
		}else {
			responseData = new ResponseData(SUCCESS,obj);
		}
		return responseData;
	}
	
	public static ResponseData success(Object obj, int total) {
		ResponseData responseData = null;
		if(obj instanceof List) {
			responseData = new ResponseData(SUCCESS,new Data(total, obj));
		}else {
			responseData = new ResponseData(SUCCESS,obj);
		}
		return responseData;
	}
	
	public static ResponseData failture(String msg) {
		ResponseData responseData = new ResponseData(FAILTURE, msg);
		return responseData;
	}
	
	public static ResponseData success(String msg) {
		ResponseData responseData = new ResponseData(SUCCESS, msg);
		return responseData;
	}
}
