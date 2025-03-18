package com.shop.cafe.service;

import com.shop.cafe.dao.LoginDao;
import com.shop.cafe.dao.UserMapper;
import com.shop.cafe.dao.WalletDao;
import com.shop.cafe.dto.Wallet;
import com.shop.cafe.dto.WalletTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

	@Autowired
	WalletDao walletDao;
	@Autowired
	LoginDao loginDao;
	@Autowired
	UserMapper userMapper;
	
	public void createWallet(Wallet w) throws Exception {
		walletDao.createWallet(w);
	};
	
    public Wallet getWallet(String token) throws Exception {
    	String email = loginDao.getEmailBy(token);
    	return walletDao.getWallet(email);
    }
    
    public int updateWallet(String token, WalletTransaction wt) throws Exception {
    	String email1 = loginDao.getEmailBy(token);
    	String email2 = walletDao.getEmailBy(wt.getAccount());
    	
    	if (email1.equals(email2)) {
    		walletDao.updateWallet(wt);
    		Wallet w = walletDao.getWallet(email1);
    		return w.getBalance();
    	}
    	else throw new IllegalAccessException("Unauthorized wallet transaction.");
    }
}
