package com.shop.cafe.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shop.cafe.dto.Login;
import com.shop.cafe.dto.Wallet;
import com.shop.cafe.dto.WalletTransaction;

@Mapper
public interface WalletDao {
    public void createWallet(Wallet w) throws Exception;
    public Wallet getWallet(String email) throws Exception;
    public void updateWallet(WalletTransaction wt) throws Exception;
	public String getEmailBy(String account);
}
