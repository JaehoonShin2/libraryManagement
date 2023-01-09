package com.library.user.service;

import java.util.ArrayList;

import com.library.common.vo.User;

public interface UserService {

	public User insert(User user);
	
	public User update(User user);
	
	public User delete(User user);
	
	public User selectOne(User user);
	
	public ArrayList<User> selectAllList();

	public ArrayList<User> selectList(User user);
	
	
}
