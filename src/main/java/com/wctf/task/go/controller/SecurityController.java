package com.wctf.task.go.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController extends BaseController{

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String auth(HttpServletRequest request) {
		removeLoginUser(request);
		return "login";
	}
}
