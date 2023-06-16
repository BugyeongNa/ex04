package com.ex01.dao;

import java.io.Reader;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.ex01.util.MybatisManager;
import com.member.mapper.MemberMapper;
import com.member.mapper.MemberSqlDivMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum ConnectionOracleUtil {
	INSTANCE;
	
	private HikariDataSource ds;
	
	private static SqlSessionFactory instance;
	
	private ConnectionOracleUtil() {
		
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

//	iBatis 설정
	
//	iBatis(MyBatis 반환)
	public SqlSessionFactory getSqlSessionFactory() {
		
		DataSource hDs = ds;
		log.info("hikariDataSource: "+hDs);
		
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		
		Environment environment = new Environment("development", transactionFactory, hDs);
		Configuration configuration = new Configuration(environment);
		
//		xml sql
//		configuration.addMapper(MemberMapper.class);
		configuration.addMapper(MemberSqlDivMapper.class);
		
		log.info("sqlSessionFactory: "+ new SqlSessionFactoryBuilder().build(configuration) );
		return new SqlSessionFactoryBuilder().build(configuration);
	}
	
//	iBatus(XML)
	public SqlSessionFactory getSqlSessionFactoryXML() {
		Reader reader = null;
		try {
			// mybatis환경 설정 읽음
			reader = Resources.getResourceAsReader("mapper/SqlConfigMapper.xml");
			// SqlSessionFactory객체를 생성
			instance = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return instance;
	}
}
