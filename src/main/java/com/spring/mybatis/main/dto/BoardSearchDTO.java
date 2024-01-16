package com.spring.mybatis.main.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardSearchDTO {
	private String searchOption;
	private String searchText;
}
