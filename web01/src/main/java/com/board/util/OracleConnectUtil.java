package com.board.util;

import java.sql.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum OracleConnectUtil {
	
	INSTANCE;
	
	private HikariDataSource ds;
	
	private OracleConnectUtil() {
		
		HikariConfig config = new HikariConfig();
		
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/xe");
		config.setUsername("user1");
		config.setPassword("1234");
		
		ds = new HikariDataSource(config);
	}
	public Connection getConnection() throws Exception{
		return ds.getConnection();
	}

}
