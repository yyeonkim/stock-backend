package com.shop.cafe.dao;

import com.shop.cafe.dto.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 사용자 등록
    void insertUser(User user);

    // 이메일로 사용자 찾기
    
    User getUserByEmail(String email);
    

    
}
