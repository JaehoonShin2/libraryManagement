package com.library.rental.service;


import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.library.common.template.FunctionTemplate;
import com.library.common.template.Template;
import com.library.common.vo.Rental;
import com.library.common.vo.User;
import com.library.rental.dao.RentalDao;
import com.library.user.dao.UserDao;

public class RentalService_impl {

	private RentalDao rentalDao;
	private SqlSession sqlSession;
	private Logger logger = Logger.getLogger(RentalService_impl.class);
	
	public RentalService_impl() {
		this.sqlSession = Template.getSqlSession();
		rentalDao = new RentalDao(sqlSession);
	}
	
	public Rental insert(Rental rental) {
		int result = rentalDao.insert(rental);
		Rental r = rentalDao.select(rental);
		if(result != 0 && r != null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return r;
	}

	public Rental update(Rental rental) {
		// rentalNo, userNo
		int result = rentalDao.update(rental);
		Rental r = rentalDao.select(rental);
		if( !FunctionTemplate.dateDiff(r.getDueDate(), r.getReturnDate())  ) {
			int point = -5;
			User u = new User(rental.getUserNo());
			u.setPoint(point);
			int pointUpdate = new UserDao(sqlSession).updatePoint(u);
			if(result == 1 && r != null 
					&& pointUpdate == 1 ) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		} else { // 정상적으로 반납이 이루어졌다면 포인트를 10점 부여함
			int point = 10;
			User u = new User(rental.getUserNo());
			u.setPoint(point);
			int pointUpdate = new UserDao(sqlSession).updatePoint(u);
			if(result == 1 && r != null 
					&& pointUpdate  == 1) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		}
		sqlSession.close();
		return r;
	}
	
	public Rental select(Rental rental) {
		Rental r = rentalDao.select(rental);
		sqlSession.close();
		return r;
	}

	public ArrayList<Rental> selectList(Rental rental) {
		ArrayList<Rental> rList = rentalDao.selectList(rental);
		sqlSession.close();
		return rList;
	}

	public int selectRentalCount(Rental rental) {
		int cnt = rentalDao.selectRentalCount(rental);
		sqlSession.close();
		return cnt;
	}

}
