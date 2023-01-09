package com.library.admin.user.dao;

import org.apache.ibatis.session.SqlSession;

import com.library.common.vo.User;

public class UserDao {

	private SqlSession sqlSession;
	
	public UserDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(User user) {
		return sqlSession.insert("userMapper.insert", user);
	}

	public User selectOne(User user) {
		return sqlSession.selectOne("userMapper.selectOne", user);
	}
	
}
