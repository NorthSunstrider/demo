package com.northsunstrider.aop.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northsunstrider.aop.service.AuthService;

@Aspect
@Component
public class SecurityAspect {

	@Autowired
	AuthService authService;

	@Pointcut("@annotation(AdminOnly)")
	public void adminOnly() {
	}

	@Before("adminOnly()")
	public void check() {
		authService.auth();
	}
}
