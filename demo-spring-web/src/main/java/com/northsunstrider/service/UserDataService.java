package com.northsunstrider.service;

import org.springframework.stereotype.Service;

import com.northsunstrider.entity.User;

@Service
public class UserDataService {
	public void saveUser(User user) {
		// save to the database
		System.out.println("save " + user + " to the database");
	}
}
