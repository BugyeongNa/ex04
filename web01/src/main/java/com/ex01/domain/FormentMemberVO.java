package com.ex01.domain;

import java.time.LocalDate;

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
public class FormentMemberVO {
	
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
	private LocalDate birthday;
	private LocalDate joinday;
	private String uuid;
}
