package com.spring.mybatis.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mybatis.board.Board;
import com.spring.mybatis.board.dao.BoardDAO;
import com.spring.mybatis.file.dao.BoardFileDAO;


@Service
public class BoardReadService {
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private BoardFileDAO filedao;
	
	public Board service(Board board) {
		return dao.view(board);
	}	
}