package com.shop.cafe.controller;

import com.shop.cafe.dto.Login;
import com.shop.cafe.dto.User;
import com.shop.cafe.dto.Wallet;
import com.shop.cafe.service.MemberService;
import com.shop.cafe.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin({"http://127.0.0.1:5500", "http://localhost:5500"})
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private WalletService walletService;

    // 사용자 등록 (회원가입)
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        
    	// 계좌 생성
        try {
			walletService.createWallet(new Wallet(user.getEmail()));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Fail to create wallet");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
        
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


 // 로그아웃 처리
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorization) {       
        try {
            memberService.logout(authorization); // DB에서 토큰 삭제
            return ResponseEntity.ok("로그아웃 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃에 실패했습니다.");
        }
    }
}
