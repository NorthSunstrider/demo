package com.northsunstrider.aop.service;

import org.springframework.stereotype.Service;

import com.northsunstrider.aop.tool.CurrentSetHolder;

@Service
public class AuthService {

	public void auth() {
		System.out.println("auth service:" + CurrentSetHolder.get());
		if (!CurrentSetHolder.get().equals("admin"))
			throw new RuntimeException("access error");

	}
}
