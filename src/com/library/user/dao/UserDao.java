package com.library.user.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.library.common.vo.User;

public class UserDao {
	
	private SqlSession sqlSession;
	
	public UserDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(User user) {
		return sqlSession.insert("userMapper.insert", user);
		
	}

	public User select(User user) {
		return sqlSession.selectOne("userMapper.select", user);
	}

	public int update(User user) {
		return sqlSession.update("userMapper.update", user);
	}
	
	public int delete(User user) {
		return sqlSession.update("userMapper.delete", user);
	}
	
	public ArrayList<User> selectList(User user) {
		return (ArrayList)sqlSession.selectList("userMapper.selectList", user);
	}

	public int updatePoint(User user) {
		return sqlSession.update("userMapper.updatePoint", user);
	}
}
