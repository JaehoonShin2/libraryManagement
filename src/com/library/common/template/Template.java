package com.library.common.template;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.mysql.cj.jdbc.ha.SequentialBalanceStrategy;

public class Template {
	
	private static SqlSessionFactory sqlSessionFactory;
	private static Logger logger = Logger.getLogger(Template.class);
	
	static {
		
		String resource = "SqlMapConfig.xml";
		InputStream stream = null;
		try {
			stream = Resources.getResourceAsStream(resource);
			logger.info(stream);
		} catch (Exception e) {
			logger.info(stream);
			logger.info("error");
		}
		
		if(sqlSessionFactory == null) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
		}
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
