package com.library.book.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.library.common.vo.Book;

public class BookDao {

	private SqlSession sqlSession;
	
	public BookDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Book select(Book book) {
		return sqlSession.selectOne("bookMapper.select", book);
	}

	public ArrayList<Book> selectList(Book book) {
		return (ArrayList)sqlSession.selectList("bookMapper.selectList", book);
	}

	public int insert(Book book) {
		return sqlSession.insert("bookMapper.insert", book);
	}

	public int delete(Book book) {
		// TODO 자동 생성된 메소드 스텁
		return sqlSession.update("bookMapper.delete", book);
	}

	
	
}
