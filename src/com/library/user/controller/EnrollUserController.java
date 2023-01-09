package com.library.user.controller;

import com.library.common.vo.User;
import com.library.user.service.UserService_Impl;

public class EnrollUserController {

	public User insert(User user) {
		return new UserService_Impl().insert(user);
	}
	
}
