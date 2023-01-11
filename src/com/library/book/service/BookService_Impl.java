package com.library.book.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.library.book.dao.BookDao;
import com.library.common.template.Template;
import com.library.common.vo.Book;

public class BookService_Impl implements BookService {

	private BookDao bookDao;
	private SqlSession sqlSession;

	public BookService_Impl() {
		sqlSession = Template.getSqlSession();
		bookDao = new BookDao(sqlSession);
	}

	@Override
	public Book select(Book book) {
		Book b = bookDao.select(book);
		sqlSession.close();
		return b;
	}

	@Override
	public ArrayList<Book> selectList(Book book) {
		ArrayList<Book> bList = bookDao.selectList(book);
		sqlSession.close();
		return bList;
	}

	@Override
	public Book insert(Book book) {
		int result = bookDao.insert(book);
		Book b = bookDao.select(book);
		if(result == 1 && b != null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return b;
	}

	@Override
	public Book delete(Book book) {
		int result = bookDao.delete(book);
		Book b = bookDao.select(book);
		if(result == 1 && b == null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return b;
	}

	@Override
	public Book update(Book book) {
		int result = bookDao.update(book);
		Book b = bookDao.select(book);
		if(result == 1 && b != null) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return b;
	}

}
