package com.study.groupware.controller;

import java.net.InetAddress;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.groupware.service.NtcReplyService;
import com.study.groupware.vo.NtcReplyVO;

@RestController
@RequestMapping("/ntcReplies")
public class NtcReplyController {
	
	 private static final Logger logger = LoggerFactory.getLogger(NtcReplyController.class);
	
	@Inject
	private NtcReplyService service;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public NtcReplyVO register(@RequestBody Map<String, Object> params,HttpServletRequest request,HttpSession session) throws Exception{
		logger.info("-------------start replyRegister [Connect IP : " + InetAddress.getLocalHost().getHostAddress() + "]");
		session = request.getSession(false);
		String stf_sq = null;
		// 세션을 불러와 admn_id에 넣는다. 없다면 null이나 ""이가 들어오겟죠
		stf_sq = (String)session.getAttribute("stf_sq");
		
		params.put("stf_sq",stf_sq);
		
		
		NtcReplyVO vo =	service.addReply(params);
		logger.info("---------------end replyRegister [Connect IP : " + InetAddress.getLocalHost().getHostAddress() + "]");
			return vo;
			
	}
	
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Map<String, Object> param) throws Exception{
		logger.info("-------------start index [Connect IP : " + InetAddress.getLocalHost().getHostAddress() + "]");
		service.removeReply(param);
		logger.info("---------------end index [Connect IP : " + InetAddress.getLocalHost().getHostAddress() + "]");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/replyMod", method = RequestMethod.POST)
	public NtcReplyVO ReplyRead(@RequestBody Map<String, Object> param) throws Exception {
		logger.info("-------------start index [Connect IP : " + InetAddress.getLocalHost().getHostAddress() + "]");
		NtcReplyVO vo = service.read(param);
		logger.info("---------------end index [Connect IP : " + InetAddress.getLocalHost().getHostAddress() + "]");
          return vo;
	}

	
	
	@ResponseBody
	@RequestMapping(value = "/replyUpdate", method = RequestMethod.POST)
	public ResponseEntity<String> replyUpdate(@RequestBody Map<String, Object> param) throws Exception {

		logger.info("-------------start index [Connect IP : " + InetAddress.getLocalHost().getHostAddress() + "]");
		 ResponseEntity<String> entity = null;
	     service.replyUpdate(param);
	     entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
	     logger.info("---------------end index [Connect IP : " + InetAddress.getLocalHost().getHostAddress() + "]");
		 return entity;
		
	}
	
	

	}
