package com.wctf.task.go.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wctf.task.go.model.User;
import com.wctf.task.go.service.TaskService;
import com.wctf.task.go.service.UserService;

@Controller
public class PageController extends BaseController {

	@Autowired
	TaskService taskService;

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		User user = userService.getUserByCode(super.getLoginUser(request));
		if (user == null)
			return "login";
		request.getSession().setAttribute("user", user);
		return "main";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/index2")
	public String index2() {
		return "index2";
	}

	@RequestMapping("/daily")
	public ModelAndView daily(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("dashboard");
		mv.addObject("todos", taskService.getTasks(getLoginUser(request)));
		return mv;
	}

	@RequestMapping("/taskprocess")
	public String taskProcess() {
		return "task-process";
	}

	@RequestMapping("/rankinglist")
	public ModelAndView rankingList() {
		ModelAndView mv = new ModelAndView("ranking-list");
		return mv;
	}

	@RequestMapping("/taskpool")
	public ModelAndView taskPool() {
		ModelAndView mv = new ModelAndView("task-pool");
		mv.addObject("todos", taskService.getTasks(null));
		return mv;
	}
}
