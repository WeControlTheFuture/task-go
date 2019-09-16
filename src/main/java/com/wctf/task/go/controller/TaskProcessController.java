package com.wctf.task.go.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wctf.task.go.model.TaskVo;
import com.wctf.task.go.model.User;
import com.wctf.task.go.service.OrgnizationService;
import com.wctf.task.go.service.TaskService;

@RestController
@RequestMapping("/taskprocess")
public class TaskProcessController extends BaseController {
	@Autowired
	private OrgnizationService orgnizationService;
	@Autowired
	private TaskService taskService;

	@RequestMapping("/list")
	public ModelAndView getAll(HttpServletRequest request) {
		List<User> users = orgnizationService.getMembers(getLoginUser(request));
		Map<User, List<TaskVo>> dataMap = taskService.getMemberTasks(users);
		ModelAndView mv = new ModelAndView("task-process");
		mv.addObject("datas", dataMap);
		return mv;
	}
}
