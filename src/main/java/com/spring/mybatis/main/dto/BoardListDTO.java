package com.spring.mybatis.main.dto;

import java.util.ArrayList;
import java.util.List;

import com.spring.mybatis.board.Board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class BoardListDTO {
	private List<Board> boardList = new ArrayList<Board>();	
	private PagingDTO pagingDTO = new PagingDTO();
	private BoardSearchDTO searchDTO = new BoardSearchDTO();
	
}
