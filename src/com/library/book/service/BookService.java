package com.library.book.service;

import java.util.ArrayList;

import com.library.common.vo.Book;

public interface BookService {

	
	Book select(Book book);

	ArrayList<Book> selectList(Book book);

	Book insert(Book book);

	Book update(Book book);
	
	Book delete(Book book);
	
}
