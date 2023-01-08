package com.librarymanagement.user.service;

import com.librarymanagement.user.vo.User;

import java.util.ArrayList;

public interface UserService {

    // 유저 생성
    User isnert(User user);

    // 유저 정보 변경
    User update(User user);

    // 유저 탈퇴
    User delete(User user);

    // 유저 정보 조회
    User select(User user);

    // 유저 정보 전체조회
    ArrayList<User> selectAll();

    // 유저 정보 조건 조회
    ArrayList<User> selectByKey(String key);


}
