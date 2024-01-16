package com.spring.mybatis.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mybatis.login.dto.LoginDTO;
import com.spring.mybatis.member.Member;

@Repository
public class LoginDAO {
	@Autowired
	SqlSession sqlSession;
	
	public Member find(LoginDTO loginDTO) {
		return sqlSession.selectOne("member.findOne", loginDTO);
	}
	
}
