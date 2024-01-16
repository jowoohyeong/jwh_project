package com.spring.mybatis.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.mybatis.board.Board;
import com.spring.mybatis.board.dao.BoardDAO;
import com.spring.mybatis.file.dao.BoardFileDAO;
import com.spring.mybatis.file.dto.BoardFileDTO;


@Service
public class BoardUpdateService {
	@Autowired
	private BoardDAO dao;

	@Autowired
	private BoardFileDAO filedao;
	
	public String service(Board board, List<MultipartFile> files, int[] deleteFile) {
		BoardFileDTO fileDTO = new BoardFileDTO();
		dao.update(board);

		if(deleteFile!=null) {
			for(int i=0;i<deleteFile.length;i++) {
				System.out.println(deleteFile[i]);
				filedao.deleteOne(deleteFile[i]);
			}
		}
		if(files!=null) {
			for(int i =0;i<files.size(); i++ ) {
				fileDTO = fileSave(files.get(i), fileDTO);
				fileDTO.setB_id(board.getId());
				filedao.insert(fileDTO);
			}
		}
		return "SUCCESS";
	}
	
	public BoardFileDTO fileSave(MultipartFile files, BoardFileDTO fileDTO) {
		String originalFile = files.getOriginalFilename();
		String fileExtension =	originalFile.substring(originalFile.lastIndexOf("."));
		//업무에서 사용하는 리눅스, UNIX는 한글지원이 안 되는 운영체제 
        //파일업로드시 파일명은 ASCII코드로 저장되므로, 한글명으로 저장 필요
        //UUID클래스 - (특수문자를 포함한)문자를 랜덤으로 생성                    "-"라면 생략으로 대체
		String storedFileName = UUID.randomUUID().toString().replaceAll("-",  "") + fileExtension;
	    String filePath = "C:\\Users\\TIGEN_221123\\eclipse-workspace\\TestMybatis\\webContent\\WEB-INF\\resources\\"; 
		
		File fileObj = new File(filePath + storedFileName);
		System.out.println(fileObj);
		try {
			files.transferTo(fileObj);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileDTO.setBf_fileName(originalFile);
		fileDTO.setBf_uploadFileName(storedFileName);
		fileDTO.setBf_filePath(filePath);
		return fileDTO;
	}
	
}