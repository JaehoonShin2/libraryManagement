package com.library.user.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.library.common.template.Template;
import com.library.common.vo.User;
import com.library.user.dao.UserDao;

public class UserService_Impl implements UserService {
	
	private SqlSession sqlSession;
	private UserDao userDao;
	
	public UserService_Impl() {
		this.sqlSession = Template.getSqlSession();
		userDao = new UserDao(sqlSession);
	}
	
	public User insert(User user) {
		int result = userDao.insert(user);
		User u = userDao.select(user);
		if(result == 1 && u != null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return u;
	}

	@Override
	public User update(User user) { 
		int result = userDao.update(user);
		User u = userDao.select(user);
		if(result == 1 && u != null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		} 
		sqlSession.close();
		return u;
	}

	@Override
	public User delete(User user) {
		int result = userDao.delete(user);
		User u = userDao.select(user);
		if(result == 1 && u == null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return u;
	}

	@Override
	public User select(User user) {
		User u = userDao.select(user);
		sqlSession.close();
		return u;
	}

	@Override
	public ArrayList<User> selectList(User user) {
		ArrayList<User> list = userDao.selectList(user);
		sqlSession.close();
		return list;
	}

}
