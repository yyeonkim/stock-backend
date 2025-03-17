package com.shop.cafe.controller;

import com.shop.cafe.dto.Login;
import com.shop.cafe.dto.User;
import com.shop.cafe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MemberService memberService;

    // 사용자 등록 (회원가입)
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        memberService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // 로그인 (토큰 생성 및 반환)
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        
        try {
            Login loginInfo = memberService.tokenLogin(user);  // tokenLogin 메서드 호출

            if (loginInfo != null && loginInfo.getToken() != null) {
                response.put("name", loginInfo.getName());  // 사용자 이름 반환
                response.put("token", loginInfo.getToken());  // 생성된 토큰 반환
                return ResponseEntity.ok(response);
            } else {
                response.put("msg", "Invalid email or password");
                return ResponseEntity.status(401).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("msg", "Login error occurred");
            return ResponseEntity.status(500).body(response);
        }
    }


    // 로그아웃
    @PostMapping("/logout")
	public void logout(@RequestHeader String authorization) {
		System.out.println(authorization);
		try {
			memberService.logout(authorization);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
