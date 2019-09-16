package com.wctf.task.go.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wctf.task.go.model.User;
import com.wctf.task.go.service.OrgnizationService;

@RestController
@RequestMapping("/orgnization")
public class OrgnizationController extends BaseController {

	@Autowired
	private OrgnizationService orgnizationService;

	@RequestMapping("/members")
	public List<User> getMembers(HttpServletRequest request) {
		return orgnizationService.getMembers(getLoginUser(request));
	}
}
