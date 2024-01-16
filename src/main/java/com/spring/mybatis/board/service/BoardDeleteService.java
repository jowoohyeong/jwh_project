package com.spring.mybatis.board.service;


import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mybatis.board.dao.BoardDAO;
import com.spring.mybatis.file.dao.BoardFileDAO;
import com.spring.mybatis.file.dto.BoardFileDTO;


@Service
public class BoardDeleteService {
	@Autowired
	private BoardDAO dao;

	@Autowired
	private BoardFileDAO filedao;
	
	public void service(int id) {
		File file;
		
		dao.delete(id);
		List<BoardFileDTO> fileDTO = filedao.fileList(id);

		filedao.deleteAll(id);
		String filePath = "C:\\Users\\TIGEN_221123\\eclipse-workspace\\TestMybatis\\webContent\\WEB-INF\\resources\\"; 
		for(int i=0;i<fileDTO.size();i++) {
			file = new File(filePath + fileDTO.get(i).getBf_uploadFileName());
			file.delete();
		}
	}
}