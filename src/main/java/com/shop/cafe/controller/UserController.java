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
@CrossOrigin("http://127.0.0.1:5500/")
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
            // 로그인 시 비밀번호 검증 및 사용자 정보 반환
            User authenticatedUser = memberService.authenticateUser(user.getEmail(), user.getPassword());

            if (authenticatedUser != null) {
                // 로그인 후 토큰 생성
                Login loginInfo = memberService.tokenLogin(authenticatedUser);

                if (loginInfo != null && loginInfo.getToken() != null) {
                    // 클라이언트에게 전달할 정보 (토큰과 사용자 이름 등)
                    response.put("name", authenticatedUser.getName());
                    response.put("token", loginInfo.getToken());

                    // 로그인 후 받은 토큰이 유효한지 validateToken으로 확인
                    boolean isValid = memberService.validateToken(loginInfo.getToken(), user.getEmail());
                    if (!isValid) {
                        response.put("msg", "Invalid token");
                        return ResponseEntity.status(401).body(response);  // 유효하지 않은 토큰
                    }

                    return ResponseEntity.ok(response); // 유효한 토큰인 경우
                } else {
                    response.put("msg", "Token generation failed");
                    return ResponseEntity.status(500).body(response);  // 토큰 생성 실패
                }
            } else {
                response.put("msg", "Invalid email or password");
                return ResponseEntity.status(401).body(response);  // 로그인 실패
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("msg", "Login error occurred");
            return ResponseEntity.status(500).body(response);  // 오류 발생 시
        }
    }



 // 로그아웃 처리
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorization) {
       System.out.println(authorization);
        String token = authorization.replace("Bearer ", "");
       
        try {
            memberService.logout(token); // DB에서 토큰 삭제
            return ResponseEntity.ok("로그아웃 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃에 실패했습니다.");
        }
    }
}
