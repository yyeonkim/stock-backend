package com.shop.cafe.controller;

import com.shop.cafe.dto.Wallet;
import com.shop.cafe.dto.WalletTransaction;
import com.shop.cafe.service.MemberService;
import com.shop.cafe.service.WalletService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;
    
    @Autowired
    private MemberService memberService;

    @GetMapping("")
    public ResponseEntity<?> getWallet(@RequestHeader(value = "Authorization", required = false)String token,
    		@RequestParam(value = "email", required = false) String email)  {
    	if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No token provided.");
        }
    	// 이메일이 없으면 401 에러 반환
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing email parameter.");
        }

        // 토큰 유효성 검사
    	boolean isValid = false;
        try {
            isValid = memberService.validateToken(token, email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired. Please log in again.");
        }
    	
    	try {
			Wallet w = walletService.getWallet(email);
			
			Map<String, Object> response = new HashMap<>();
			response.put("username", w.getUsername());
			response.put("account", w.getAccount());
			response.put("balance", w.getBalance());
			
			return ResponseEntity.ok().body(response);
		} catch (NullPointerException e) {
			// 로그인 정보 없음
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
    
    @PostMapping("")
    public ResponseEntity<?> updateWallet(@RequestHeader("Authorization") String token,
            @RequestParam("email") String email,
            @RequestBody WalletTransaction wt) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No token provided.");
        }

        // 토큰 유효성 검사
        boolean isValid = false;
        try {
            isValid = memberService.validateToken(token, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired. Please log in again.");
        }

        try {
            walletService.updateWallet(wt);
            return ResponseEntity.ok().body("Wallet updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error.");
        }
    }

   
}
