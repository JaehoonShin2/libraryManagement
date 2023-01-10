package com.library.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private String isbn;
	private String title;
	private String date;
	private int page;
	private int price;
	private String author;
	private String translator;
	private String publisher;
	private String bimgurl;
	private String status;
	
	private String rentYN;
	
	
	public Book(String title) {
		this.title = title;
	}
	
}
