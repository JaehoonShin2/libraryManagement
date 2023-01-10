package com.library.user.service;

import java.util.ArrayList;

import com.library.common.vo.User;

public interface UserService {

	User insert(User user);
	
	User update(User user);
	
	User delete(User user);

	User select(User user);

	ArrayList<User> selectList(User user);
	
	
}
