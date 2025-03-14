package com.shop.cafe.dao;

import com.shop.cafe.dto.User;

import org.apache.ibatis.annotations.Mapper;  // @Mapper 추가!

@Mapper
public interface UserMapper {

    // 사용자 등록

    void insertUser(User user);

    // 사용자 로그인
    
    User getUserByEmail(String email);
}
