package com.northsunstrider.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.northsunstrider.aop.security.AdminOnly;

@Service
public class ProductService {

	@Autowired
	AuthService authService;

	public void insert() {
		authService.auth();
		System.out.println("insert product");
	}

	@AdminOnly
	public void delete() {
		System.out.println("delete product");
	}
}
