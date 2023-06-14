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
public class FormentMemberDTO {
	
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
	private LocalDate birthday;
	private LocalDate joinday;
	private String uuid;
}
