package com.spring.mybatis.board;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.spring.mybatis.file.dto.BoardFileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
	private int id;
	private String title;
	private String content;
	private String writer;
	private Date created_date;
	
	private int reid;
    private int relev;		/** 답글 계층 **/
    private int reord;
    
    private List<BoardFileDTO> fileDTO = new ArrayList<BoardFileDTO>();
}
