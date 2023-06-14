package com.ex01.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ex01.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;
@Log4j2
class TodoServiceTest {
	
	private TodoService todoService;
	
	@BeforeEach
	public void read() throws Exception{
		todoService = TodoService.INSTANCE;
	}
	
	@Test
	void testRegister() throws Exception {
		TodoDTO dto = TodoDTO.builder()
				.title("JDBC Test Title3")
				.dueDate(LocalDate.now())
				.build();
		
		System.out.println(dto);
		
		log.info("\n---dto: "+dto);
		
		
		todoService.register(dto);
	}

}

//	소스 경로
//	자바 소스 : src>main>java
//	리소스 : src>main>resources
//	웹(web) : src>main>webapp
