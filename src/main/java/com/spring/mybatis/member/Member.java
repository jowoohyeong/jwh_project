package com.spring.mybatis.member;

import java.sql.Date;

import lombok.Data;

@Data
public class Member {
	private String m_id;
	private String m_pwd;
	private String m_name;
	private int m_phone;
	private Date m_date;
}
