package com.wctf.task.go.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

public abstract class BaseController {
	protected String getLoginUser(HttpServletRequest request) {
		SecurityContextImpl auth = (SecurityContextImpl) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		return auth.getAuthentication().getName();
	}

	protected void removeLoginUser(HttpServletRequest request) {
		request.getSession().removeAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	}
}
