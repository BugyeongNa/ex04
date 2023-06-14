package com.ex01.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
	private Long tno;
	private String title;
	private LocalDate dueDate;
	private boolean finished;
}
