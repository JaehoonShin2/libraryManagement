package com.library.user.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSessionFactory;

import com.library.common.template.Template;
import com.library.common.vo.User;
import com.library.user.dao.UserDao;

public class UserService_Impl implements UserService {
	
	private SqlSessionFactory sqlSessionFactory;
	private UserDao userDao;
	
	public UserService_Impl() {
		sqlSessionFactory = Template.getSqlSessionFactory();
		userDao = new UserDao(sqlSessionFactory);
	}
	
	public User insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public User update(User user) { 
		return userDao.update(user);
	}

	@Override
	public User delete(User user) {
		return userDao.delete(user);
	}

	@Override
	public User selectOne(User user) {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public ArrayList<User> selectAllList() {
		return userDao.selectAllList();
	}

	@Override
	public ArrayList<User> selectList(User user) {
		return userDao.selectList(user);
	}

}
