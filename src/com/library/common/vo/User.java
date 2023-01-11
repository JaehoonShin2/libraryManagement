package com.library.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private String phone;
	private int point;
	private int grade;
	private int rentalCount;
	private int maxRentalCount;
	private String status;
	private String adminYN;
	

	// 포인트 변경을 위해 객체를 생성하는 메소드
	public User(int userNo) {
		this.userNo = userNo;
	}


	public User(String userId, String userPwd, String userName, String phone, String gender) {
		this.userId = userId;
		this.userPwd= userPwd;
		this.userName = userName;
		this.phone = phone;
		this.gender = gender;
	}

}
