package com.spring.mybatis.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mybatis.board.service.BoardService;
import com.spring.mybatis.login.dto.LoginDTO;
import com.spring.mybatis.login.service.LoginService;


@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	BoardService boardService;
	@Autowired
	LoginService boardlogin;
	
	@RequestMapping("/")
	public String login() {
		System.out.println("//////////////////////////////");
		logger.error(" >>> /");
		return "redirect:/login";
	}
	@RequestMapping("/login")
	public String login(HttpSession session){
		logger.info("LoginController.java . login");
		return "/login";
	}
	@RequestMapping("/signup")
	public String signup(){
		return "/signup";
	}
	@ResponseBody
	@RequestMapping(value="/loginCheck", produces = "text/html;charset=UTF-8")
	public String loginCheck(LoginDTO loginDTO, Model model, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute("userId", loginDTO.getM_id());
		return boardlogin.service(loginDTO);

	}
}
