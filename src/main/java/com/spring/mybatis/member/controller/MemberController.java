package com.spring.mybatis.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mybatis.member.Member;
import com.spring.mybatis.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/insertMember")
	public String insertMember(Member member, Model model){
		model.addAttribute("result", memberService.service(member));
		return "/signResult";
	}
}
