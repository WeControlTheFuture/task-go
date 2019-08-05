package com.wctf.task.go.controller;

import java.io.File;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wctf.task.go.model.AttechmentUploadResponse;
import com.wctf.task.go.model.BaseResponse;
import com.wctf.task.go.model.CreateTaskParam;
import com.wctf.task.go.service.TaskService;
import com.wctf.task.go.service.UserService;
import com.wctf.task.go.utils.FileUtil;
import com.wctf.task.go.utils.TimeUtil;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;

	@Value("${task.upload.path}")
	String savePath;

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
	public AttechmentUploadResponse taskAttechment(HttpServletRequest request, @RequestParam(value = "id") Integer id, @RequestParam(value = "file", required = false) MultipartFile multipartFile) {
		AttechmentUploadResponse response = new AttechmentUploadResponse();
		if (multipartFile != null) {
			System.out.print(multipartFile.getOriginalFilename() + "=====" + multipartFile);
			try {
				File saveFile = FileUtil.getMonthlyPathFile(savePath, multipartFile.getOriginalFilename());
				multipartFile.transferTo(saveFile);
				Timestamp createTs = taskService.addAttechment(super.getLoginUser(request), id, multipartFile.getOriginalFilename(), saveFile.getAbsolutePath());
				response.setCode(0);
				response.setUserName(userService.getUserByCode(super.getLoginUser(request)).getName());
				response.setCreateTs(TimeUtil.yyyyMMddHHmmSSFormatTs(createTs));
			} catch (Exception e) {
				response.setCode(1);
				response.setMsg("exception");
				e.printStackTrace();
			}
		} else {
			response.setCode(2);
			response.setMsg("upload file is empty");
		}
		return response;
	}
}
