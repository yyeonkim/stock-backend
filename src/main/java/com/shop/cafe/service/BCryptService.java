package com.shop.cafe.service;

import com.shop.cafe.dao.UserMapper;
import com.shop.cafe.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptService {

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 사용자 등록 (비밀번호 암호화)
    public void registerUser(User user) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.insertUser(user);
    }

    // 로그인 시 비밀번호 확인
    public boolean authenticateUser(String email, String password) {
        User user = userMapper.getUserByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
