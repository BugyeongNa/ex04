package com.ex01.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberVO {
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String uuid;
}
