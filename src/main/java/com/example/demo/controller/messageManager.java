package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

import com.example.demo.dao.IMessageHumanRepository;
import com.example.demo.util.ResponseData;
import com.example.demo.vo.messageHuman;

@RestController
@RequestMapping(value = {"/api"})
public class messageManager {

	@Autowired
	IMessageHumanRepository messageHumanRepository;
	
	@RequestMapping(value = {"/message/add"},method = {RequestMethod.POST})
	public ResponseData addMessage(
			@RequestBody messageHuman messagehuman
			) {
		messageHuman mh;
		if (messagehuman.getStatus() == 2) {
			mh = messageHumanRepository.findByProidAndStatus(messagehuman.getProid(), messagehuman.getStatus());
			if (mh != null) {
				mh.setIscheck(messagehuman.getIscheck());
				mh.setSenddate(messagehuman.getSenddate());
				mh.setReason(messagehuman.getReason());
				messageHumanRepository.saveAndFlush(mh);
			} else {
				messageHumanRepository.saveAndFlush(messagehuman);
			}
		} else if (messagehuman.getStatus() == 0 || messagehuman.getStatus() == 1) {
			mh = messageHumanRepository.findByProidAndStatusAndEmpnum(messagehuman.getProid(), messagehuman.getStatus(), messagehuman.getEmpnum());
			if (mh != null) {
				mh.setIscheck(messagehuman.getIscheck());
				mh.setSenddate(messagehuman.getSenddate());
				mh.setReason(messagehuman.getReason());
				mh.setIdentity(messagehuman.getIdentity());
				messageHumanRepository.saveAndFlush(mh);
			} else {
				messageHumanRepository.saveAndFlush(messagehuman);
			}
		} else if (messagehuman.getStatus() == 4) {
			mh = messageHumanRepository.findByProidAndStatus(messagehuman.getProid(), messagehuman.getStatus());
			if (mh != null) {
				mh.setIscheck(messagehuman.getIscheck());
				mh.setSenddate(messagehuman.getSenddate());
				messageHumanRepository.saveAndFlush(mh);
			} else {
				messageHumanRepository.saveAndFlush(messagehuman);
			}
		} else {
			mh = messageHumanRepository.findByProidAndStatusAndEmpnum(messagehuman.getProid(), messagehuman.getStatus(), messagehuman.getEmpnum());
			if (mh != null) {
				mh.setIscheck(messagehuman.getIscheck());
				mh.setSenddate(messagehuman.getSenddate());
				mh.setReason(messagehuman.getReason());
				mh.setIdentity(messagehuman.getIdentity());
				messageHumanRepository.saveAndFlush(mh);
			} else {
				messageHumanRepository.saveAndFlush(messagehuman);
			}
		}
//		mh = messageHumanRepository.findByProidAndStatusAndEmpnum(messagehuman.getProid(), messagehuman.getStatus(), messagehuman.getEmpnum());
		
//		if (mh != null) {
//			mh.setSend(messagehuman.getSend());
//			mh.setAccept(messagehuman.getAccept());
//			mh.setIdentity(messagehuman.getIdentity());
//			mh.setStatus(messagehuman.getStatus());
//			mh.setIscheck(messagehuman.getIscheck());
//			mh.setSenddate(messagehuman.getSenddate());
//			mh.setEmpname(messagehuman.getEmpname());
//			mh.setAcceptname(messagehuman.getAcceptname());
//			mh.setSendname(messagehuman.getSendname());
//			mh.setReason(messagehuman.getReason());
//			messageHumanRepository.saveAndFlush(mh);
//		} else {
//			messageHumanRepository.saveAndFlush(messagehuman);
//		}		
		return ResponseData.success();
	}
	
	@RequestMapping(value = {"/message/getallmessgae"},method = {RequestMethod.POST})
	public ResponseData getAllMessgae(
			@RequestBody Map<String, Object> params
			) {
		String accept = params.get("accept").toString();
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		
		Sort sort =  new Sort(Direction.DESC, "senddate");
		Pageable pageable = new PageRequest(page,count,sort);
		Page<messageHuman> messages = messageHumanRepository.findByAccept(accept, pageable);
		
		return ResponseData.success(messages, (int)messages.getTotalElements());
	}
	
	@RequestMapping(value = {"/message/updatecheck"},method = {RequestMethod.POST})
	public ResponseData updateCheck(
			@RequestBody Map<String, Object> params
			) {
		String proid =  params.get("proid").toString();
		String empnum =  params.get("empnum").toString();
		String reason =  params.get("reason").toString();
		int status =  Integer.parseInt(params.get("status").toString());
		messageHuman mh = messageHumanRepository.findByProidAndStatusAndEmpnum(proid, status, empnum);
		mh.setIscheck(1);
		if (reason != null && reason.length() != 0) {
			mh.setReason(reason);
		}
		messageHumanRepository.saveAndFlush(mh);
		return ResponseData.success();
	}
	
	// 获取申请的请求
	@RequestMapping(value = {"/message/getcheckedmessage"},method = {RequestMethod.POST})
	public ResponseData getCheckedMessgae(
			@RequestBody Map<String, Object> params
			) {
		String send = params.get("send").toString();
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		
		Sort sort =  new Sort(Direction.DESC, "senddate");
		Pageable pageable = new PageRequest(page,count,sort);
		
		Specification<messageHuman> specification = new Specification<messageHuman>(){
			@Override
			public Predicate toPredicate(Root<messageHuman> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.notEqual(root.get("status"), 2));
				predicates.add(cb.notEqual(root.get("status"), 4));
				// TODO Auto-generated method stub
				if (!StringUtils.isEmpty(send)) {
					String wrapSearch = "%" + send + "%";
					predicates.add(cb.like(root.get("send"), wrapSearch));
				}
				predicates.add(cb.equal(root.get("ischeck"), 1));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}			
		};
//		Page<messageHuman> messages = messageHumanRepository.findBySendAndIscheck(send, 1, pageable);
//		List<messageHuman> mh = messageHumanRepository.findBySendAndIscheck(send, 1);
//		mh.addAll(messageHumanRepository.findByAccept(send));
		Page<messageHuman> messages = messageHumanRepository.findAll(specification, pageable);
		return ResponseData.success(messages, (int)messages.getTotalElements());
	}
	
	@RequestMapping(value = {"/message/getacceptmessgae"},method = {RequestMethod.POST})
	public ResponseData getAcceptMessgae(
			@RequestBody Map<String, Object> params
			) {
		String accept = params.get("accept").toString();
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		
		Sort sort =  new Sort(Direction.DESC, "senddate");
		Pageable pageable = new PageRequest(page,count,sort);
		Page<messageHuman> messages = messageHumanRepository.findByAccept(accept, pageable);
		
		return ResponseData.success(messages, (int)messages.getTotalElements());
	}
	
	@RequestMapping(value = {"/message/getsendmessage"},method = {RequestMethod.POST})
	public ResponseData getSendMessage(
			@RequestBody Map<String, Object> params
			) {
		String send = params.get("send").toString();
		int page = Integer.parseInt(params.get("page").toString()) - 1;
		int count = Integer.parseInt(params.get("count").toString());
		
		Sort sort =  new Sort(Direction.DESC, "senddate");
		Pageable pageable = new PageRequest(page,count,sort);
		Page<messageHuman> messages = messageHumanRepository.findBySend(send, pageable);
		
		return ResponseData.success(messages, (int)messages.getTotalElements());
	}
	
	@RequestMapping(value = {"/message/getnocheckedmessgae"},method = {RequestMethod.POST})
	public ResponseData getNoCheckedMessgae(
			@RequestBody Map<String, Object> params
			) {
		String send = params.get("send").toString();
		String proid = params.get("proid").toString();
		List<messageHuman> mh = messageHumanRepository.findByProidAndSendAndIscheck(proid, send, 0);
		return ResponseData.success(mh);
	}
	
	@RequestMapping(value = {"/message/getmessagedetail"},method = {RequestMethod.POST})
	public ResponseData getMessageDetail(
			@RequestBody Map<String, Object> params
			) {
		String proid =  params.get("proid").toString();
		String empnum =  params.get("empnum").toString();
		int status =  Integer.parseInt(params.get("status").toString());
		messageHuman mh = messageHumanRepository.findByProidAndStatusAndEmpnum(proid, status, empnum);
		return ResponseData.success(mh);
	}
	
	@RequestMapping(value = {"/message/updateplancheck"},method = {RequestMethod.POST})
	public ResponseData updatePlanCheck(
			@RequestBody Map<String, Object> params
			) {
		String proid =  params.get("proid").toString();
		int status =  Integer.parseInt(params.get("status").toString());
		messageHuman mh = messageHumanRepository.findByProidAndStatus(proid, status);
		mh.setIscheck(1);
		messageHumanRepository.saveAndFlush(mh);
		return ResponseData.success();
	}
}
