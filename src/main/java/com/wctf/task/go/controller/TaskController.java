package com.wctf.task.go.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wctf.task.go.model.BaseResponse;
import com.wctf.task.go.model.CreateTaskParam;
import com.wctf.task.go.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

	@Autowired
	TaskService taskService;

	String SAVE_PATH = "/Users/bixy/sc_attech";

	@RequestMapping(value = "/create", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResponse createTask(HttpServletRequest request, @RequestBody CreateTaskParam param) {
		taskService.create(getLoginUser(request), param);
		return new BaseResponse("success", 1);
	}

	@RequestMapping(value = "/detail", method = { RequestMethod.GET })
	public ModelAndView taskDetail(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView("component/task_details");
		mv.addObject("task", taskService.getTask(id));
		return mv;
	}

	@PostMapping(value = "/attechment")
	@ResponseBody
	public BaseResponse taskAttechment(@RequestParam(value = "file", required = false) MultipartFile mpf) {
		boolean flag = false;
		if (mpf != null) {
			System.out.print(mpf.getOriginalFilename() + "=====" + mpf);
			try {
				mpf.transferTo(getFile());
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
			flag = true;
		}
		return mpf != null ? flag ? new BaseResponse("上传成功", 200) : new BaseResponse("上传失败", 200) : new BaseResponse("上传失败", 300);
	}

	private File getFile() {
		return null;
	}
}
