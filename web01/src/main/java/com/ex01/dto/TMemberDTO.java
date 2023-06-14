package com.ex01.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TMemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	private String uuid;
}
