package com.wctf.task.go.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wctf.task.go.dao.TaskAttrMapper;
import com.wctf.task.go.dao.TaskMapper;
import com.wctf.task.go.dao.UserMapper;
import com.wctf.task.go.model.CreateTaskParam;
import com.wctf.task.go.model.Task;
import com.wctf.task.go.model.TaskAttr;
import com.wctf.task.go.model.TaskAttrType;
import com.wctf.task.go.model.TaskStatus;
import com.wctf.task.go.model.TaskVo;
import com.wctf.task.go.model.TaskVoAttr;
import com.wctf.task.go.model.User;
import com.wctf.task.go.utils.TimeUtil;

@Service
public class TaskService {

	@Autowired
	TaskMapper taskMapper;

	@Autowired
	TaskAttrMapper taskAttrMapper;

	@Autowired
	UserMapper userMapper;

	public void create(String creater, CreateTaskParam param) {
		param.setCreater(creater);
		taskMapper.create(param);
	}

	public List<TaskVo> getTasks(String assignee) {
		List<Task> tasks = taskMapper.getTaskExcludeStatus(assignee, TaskStatus.DONE);
		if (CollectionUtils.isNotEmpty(tasks)) {
			return tasks.parallelStream().map(r -> {
				TaskVo tv = new TaskVo();
				try {
					BeanUtils.copyProperties(tv, r);
					if (r.getStopDate() != null)
						tv.setLeftDays(TimeUtil.getLeftDays(r.getStopDate()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return tv;
			}).collect(Collectors.toList());
		}
		return null;
	}

	public TaskVo getTask(Integer id) {
		Task task = taskMapper.getTaskById(id);
		TaskVo tv = new TaskVo();
		try {
			BeanUtils.copyProperties(tv, task);
			if (task.getStopDate() != null)
				tv.setLeftDays(TimeUtil.getLeftDays(task.getStopDate()));
			tv.setCreateUser(userMapper.getUserSimpleInfo(task.getCreater()));
			Map<TaskAttrType, List<TaskVoAttr>> attrs = getTaskAttrs(id);
			if (MapUtils.isNotEmpty(attrs)) {
				tv.setAttachments(attrs.get(TaskAttrType.ATTACHMENT));
				tv.setComments(attrs.get(TaskAttrType.COMMENT));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tv;
	}

	private Map<TaskAttrType, List<TaskVoAttr>> getTaskAttrs(Integer taskId) {
		List<TaskAttr> attrs = taskAttrMapper.getTaskAttrByTaskId(taskId);
		if (CollectionUtils.isEmpty(attrs))
			return null;
		Map<String, User> userCache = new HashMap<>();
		return attrs.stream().map(r -> {
			TaskVoAttr attr = new TaskVoAttr();
			try {
				BeanUtils.copyProperties(attr, r);
			} catch (Exception e) {
				e.printStackTrace();
			}
			attr.setUser(userCache.computeIfAbsent(r.getUserCode(), k -> userMapper.getUserSimpleInfo(r.getUserCode())));
			return attr;
		}).collect(Collectors.groupingBy(TaskVoAttr::getType));
	}

	public void createComment(TaskAttr attr) {
		attr.setType(TaskAttrType.COMMENT);
		taskAttrMapper.createTaskAttr(attr);
	}
}
