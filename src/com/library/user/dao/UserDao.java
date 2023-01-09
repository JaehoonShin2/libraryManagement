package com.library.user.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.library.common.vo.User;

public class UserDao {
	
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
	public UserDao(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public User insert(User user) {
		sqlSession = sqlSessionFactory.openSession(false);
		
		int result = sqlSession.insert("userMapper.insert", user);
		System.out.println(user.getUserId());
		System.out.println(user.getUserPwd());
		User u = sqlSession.selectOne("userMapper.selectOne", user);
		if(result == 1 && u != null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return u;
	}
	

	public User selectOne(User user) {
		return sqlSession.selectOne("userMapper.select", user);
	}

	public User update(User user) {
		sqlSession = sqlSessionFactory.openSession(false);
		int result = sqlSession.update("userMapper.update", user);
		User u = sqlSession.selectOne("userMapper.select", user);
		if(result == 1 && u != null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		} 
		sqlSession.close();
		return u;
	}
	
	public User delete(User user) {
		sqlSession = sqlSessionFactory.openSession(false);
		int result = sqlSession.update("userMapper.delete", user);
		User u = sqlSession.selectOne("userMapper.select", user);
		if(result == 1 && u == null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return u;
	}

	public ArrayList<User> selectAllList() {
		sqlSession = sqlSessionFactory.openSession(false);
		ArrayList<User> userList = (ArrayList)sqlSession.selectList("userMapper.selectAll");
		sqlSession.close();
		return userList;
	}

	public ArrayList<User> selectList(User user) {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}
}
