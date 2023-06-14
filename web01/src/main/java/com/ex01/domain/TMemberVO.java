package com.ex01.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TMemberVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	private String uuid;
	
}
