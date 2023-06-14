package com.ex01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String uuid;
}
