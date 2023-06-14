package com.board.domain;

import java.time.LocalDate;

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
public class BoardVO {
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private String imageFileName;
	private LocalDate writedate;
	private String id;
	private int level;
}
