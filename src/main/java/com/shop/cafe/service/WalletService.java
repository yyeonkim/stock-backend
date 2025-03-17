package com.shop.cafe.service;

import com.shop.cafe.dao.WalletDao;
import com.shop.cafe.dto.Wallet;
import com.shop.cafe.dto.WalletTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

	@Autowired
	WalletDao walletDao;
	
	public void createWallet(Wallet w) throws Exception {
		walletDao.createWallet(w);
	};
	
    public Wallet getWallet(String email) throws Exception {
    	return walletDao.getWallet(email);
    }
    
    public void updateWallet(WalletTransaction wt) throws Exception {
    	walletDao.updateWallet(wt);
    }
}
