package com.board.dto;

import java.time.LocalDate;

import com.board.dao.BoardDAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private String imageFileName;
	private LocalDate writedate;
	private String id;
	private int level;
}
