package com.library.user.user.service;

import org.apache.ibatis.session.SqlSession;

import com.library.admin.user.dao.UserDao;
import com.library.common.template.Template;
import com.library.common.vo.User;

public class UserService_Impl {
	
	private SqlSession sqlSession;
	private UserDao userDao;
	
	public UserService_Impl() {
		sqlSession = Template.getSqlSessionFactory().openSession(false);
		userDao = new UserDao(sqlSession);
	}
	
	public User insert(User user) {
		int result = userDao.insert(user);
		User u = userDao.selectOne(user);
		if(result == 1 && u != null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return u;
	}

}
