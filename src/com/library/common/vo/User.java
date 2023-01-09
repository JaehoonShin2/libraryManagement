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
	private int maxRentalCount;
	private String status;
	private String adminYN;
	

}
