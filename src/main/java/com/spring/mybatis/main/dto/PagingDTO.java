package com.spring.mybatis.main.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingDTO {
	private int currentPage = 1;
	private int totalCount;
	
	private int startNo;
	private int endNo;
	
	private int blockCount = 10;
	
	public int getStartNo() {
		return (currentPage - 1) * blockCount;
	}
	
	private String pagingHtml;
}
