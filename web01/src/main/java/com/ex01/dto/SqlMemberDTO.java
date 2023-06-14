package com.ex01.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SqlMemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
	private LocalDate joindate;
	private int memno;
}
