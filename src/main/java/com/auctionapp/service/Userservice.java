package com.auctionapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.auctionapp.model.Users;
import com.auctionapp.repository.UsersRepository;

public class Userservice {
	@Autowired
	UsersRepository repo;

	public String verifyUser(Users usr) {
		//StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	//	encryptor.setPassword(seed);

	//	String decrypted = encryptor.decrypt(encrypted);F
		return null;
		
	}
}
