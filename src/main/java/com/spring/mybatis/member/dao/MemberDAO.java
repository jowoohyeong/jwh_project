package com.spring.mybatis.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mybatis.member.Member;

@Repository
public class MemberDAO {
	@Autowired
	SqlSession sqlSession;
	
	public Member find(Member member) {
		return sqlSession.selectOne("member.findOne", member);
	}
	public List<Member> findAll() {
		return sqlSession.selectList("member.list");
	}
	public int memberCount() {
		return sqlSession.selectOne("member.count");
	}
	public void insert(Member member) {
		sqlSession.insert("member.insert", member);
	}
}
