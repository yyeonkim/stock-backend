package com.shop.cafe.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shop.cafe.dto.Login;

@Mapper
public interface LoginDao {

    // 로그인 시 토큰 저장
    
    public void insertToken(Login login) throws Exception;

    // 로그아웃 시 토큰 삭제
    
    public void deleteToken(String token) throws Exception;
    
    public String getEmailBy(String token) throws Exception;
}
