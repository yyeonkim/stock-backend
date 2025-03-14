package com.shop.cafe.controller;

import com.shop.cafe.dto.User;
import com.shop.cafe.service.BCryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private BCryptService bCryptService;

    // 사용자 등록
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        bCryptService.registerUser(user);
        return "User registered successfully!";
    }

    // 로그인 (비밀번호 검증)
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        boolean isAuthenticated = bCryptService.authenticateUser(user.getEmail(), user.getPassword());
        if (isAuthenticated) {
            return "Login successful!";
        }
        return "Invalid email or password!";
    }
}
