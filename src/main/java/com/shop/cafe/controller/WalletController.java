package com.shop.cafe.controller;

import com.shop.cafe.dto.Wallet;
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

    @GetMapping("")
    public ResponseEntity<?> getWallet(@RequestHeader("Authorization") String authorization)  {
    	Map<String, Object> response = new HashMap<>();
    	
    	try {
			Wallet w = walletService.getWallet(authorization);
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
   
}
