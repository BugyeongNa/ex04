package com.ex01.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {
	private Long tno;
	private String title;
	private LocalDate dueDate;
	private boolean finished;
}

//	ModelMapper 라이브러리 사용
//	DTO/VO구분
//	DTO => VO, VO => DTO