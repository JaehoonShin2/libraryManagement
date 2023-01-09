package com.library.common.template;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class Template {
	
	private static SqlSessionFactory sqlSessionFactory;
	private static Logger logger = Logger.getLogger(Template.class);
	
	static {
		
		String resource = "SqlMapConfig.xml";
		
		try {
			InputStream stream = Resources.getResourceAsStream(resource);
			if(sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
			}
		} catch (Exception e) {
			logger.info("error");
		}
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
