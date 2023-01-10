package com.library.rental.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.library.common.vo.Rental;
import com.library.common.vo.User;

public class RentalDao {

	private SqlSession sqlSession;
	
	public RentalDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(Rental rental) {
		return sqlSession.insert("rentalMapper.insert", rental);
	}

	public int update(Rental rental) {
		return sqlSession.update("rentalMapper.update", rental);
	}

	public Rental select(Rental rental) {
		return sqlSession.selectOne("rentalMapper.select", rental);
	}

	public ArrayList<Rental> selectList(Rental rental) {
		return (ArrayList)sqlSession.selectList("rentalMapper.selectList", rental);
	}

	public int selectRentalCount(Rental rental) {
		return sqlSession.selectOne("rentalMapper.selectRentalCount", rental);
	}

}
