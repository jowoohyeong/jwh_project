package com.spring.mybatis.board;

import java.util.List;

import com.spring.mybatis.file.dto.BoardFileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BoardViewDTO {
	private Board board;
	private List<BoardFileDTO> fileDTO;
}
