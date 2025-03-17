package com.shop.cafe.controller;

import com.shop.cafe.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

   
}
