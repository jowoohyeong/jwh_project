package com.spring.mybatis.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mybatis.member.Member;
import com.spring.mybatis.member.dao.MemberDAO;

@Service
public class MemberService {
	@Autowired	
	MemberDAO dao;
	
	public String service(Member member) {
		System.out.println("member: "+ member);
		System.out.println("----> count : " + dao.memberCount());
		System.out.println("----> find: " + dao.find(member));
		System.out.println("----> member list: " + dao.findAll());
		
		if(dao.find(member)==null) {
			dao.insert(member);
			return "SUCCESS";
		}else {
			return "FAIL";
		}
	}
}
