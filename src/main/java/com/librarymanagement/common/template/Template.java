package com.librarymanagement.common.template;

import com.librarymanagement.common.log.MyLogger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Template {


    public static SqlSession getSqlSession(){

        SqlSession sqlSession = null;

        String resource = "/main/resources/mybatis-config.xml";
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
        } catch ( Exception e) {
            MyLogger.getLogger("sqlSession error");
        }
        return sqlSession;
    };
}
