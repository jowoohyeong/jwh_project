package com.spring.mybatis.file.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {
	private int bf_id;
	private int b_id;
	private String bf_fileName;
	private String bf_uploadFileName;
	private String bf_filePath;

}
