package com.spring.mybatis.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mybatis.file.dto.BoardFileDTO;

@Repository
public class BoardFileDAO {
	@Autowired
	SqlSession sqlSession;
	
	public void insert(BoardFileDTO fileDTO) {
		sqlSession.insert("boardFile.insert", fileDTO);
	}
	public void deleteAll(int id) {
		sqlSession.insert("boardFile.deleteAll", id);
	}
	public void deleteOne(int id) {
		sqlSession.insert("boardFile.deleteOne", id);
	}
	public List<BoardFileDTO> fileList(int id) {
		return sqlSession.selectList("boardFile.fileList", id);
	}
}

