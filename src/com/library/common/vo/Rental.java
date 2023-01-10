package com.library.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Rental {
	
	private int rentalNo;
	private int userNo;
	private String userId;
	private String isbn;
	private String title;
	private String rentalDate;
	private String dueDate;
	private String returnDate;
	private String status;
	
	
	// 유저의 대출 정보 업데이트 시 필요한 객체를 생성하는 메소드
	public Rental(int rentalNo, int userNo) {
		// TODO 자동 생성된 생성자 스텁
	}
	
	// 대출 등록 시 필요한 객체를 생성하는 메소드
	public Rental(int userNo, String isbn) {
		this.userNo = userNo;
		this.isbn = isbn;
	}

	// 유저의 대출 도서 조회 시 필요한 객체를 생성하는 메소드
	public Rental(int userNo) {
		this.userNo = userNo;
	}
	
}
