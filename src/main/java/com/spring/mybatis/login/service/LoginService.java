package com.spring.mybatis.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mybatis.login.dao.LoginDAO;
import com.spring.mybatis.login.dto.LoginDTO;
import com.spring.mybatis.member.Member;

@Service
public class LoginService {
	@Autowired
	LoginDAO dao;
	
	public String service(LoginDTO loginDTO) {
		Member result = dao.find(loginDTO);
		
		System.out.println("loginDTO: "+loginDTO+"\nresult: "+result);
		
		if(result.getM_id().equals(loginDTO.getM_id()) 
				&& result.getM_pwd().equals(loginDTO.getM_pwd())) {		//둘다 일치
			return "SUCCESS";
		}
		else {			
			return "FAIL";
		}
	}
}
