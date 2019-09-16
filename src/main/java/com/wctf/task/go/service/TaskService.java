package com.wctf.task.go.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.wctf.task.go.dao.TaskAttrMapper;
import com.wctf.task.go.dao.TaskMapper;
import com.wctf.task.go.dao.UserMapper;
import com.wctf.task.go.model.Attachment;
import com.wctf.task.go.model.Comment;
import com.wctf.task.go.model.CreateTaskParam;
import com.wctf.task.go.model.Task;
import com.wctf.task.go.model.TaskAttr;
import com.wctf.task.go.model.TaskAttrType;
import com.wctf.task.go.model.TaskStatus;
import com.wctf.task.go.model.TaskVo;
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

	public Map<User, List<TaskVo>> getMemberTasks(List<User> users) {
		if (CollectionUtils.isEmpty(users))
			return Collections.emptyMap();
		Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getCode, r -> r));
		List<Task> tasks = taskMapper.getTasksExcludeStatus(new ArrayList<String>(userMap.keySet()), TaskStatus.CLOSE);
		if (CollectionUtils.isEmpty(tasks))
			return Collections.emptyMap();
		Map<User, List<TaskVo>> resultMap = new HashMap<User, List<TaskVo>>();
		tasks.forEach(t -> {
			List<TaskVo> taskVos = resultMap.computeIfAbsent(userMap.get(t.getAssignee()), k -> new ArrayList<>());
			TaskVo tv = new TaskVo();
			try {
				BeanUtils.copyProperties(tv, t);
				taskVos.add(tv);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return resultMap;
	}

	public void create(String creater, CreateTaskParam param) {
		param.setCreater(creater);
		taskMapper.create(param);
	}

	public List<TaskVo> getTasks(String assignee) {
		List<Task> tasks = taskMapper.getTaskExcludeStatus(assignee, Arrays.asList(TaskStatus.DONE, TaskStatus.CLOSE));
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
			setTaskAttrs(tv, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tv;
	}

	private void setTaskAttrs(TaskVo tv, Integer taskId) {
		List<TaskAttr> attrs = taskAttrMapper.getTaskAttrByTaskId(taskId);
		if (CollectionUtils.isEmpty(attrs))
			return;
		Map<String, User> userCache = new HashMap<>();
		attrs.stream().forEach(r -> {
			if (r.getType().equals(TaskAttrType.COMMENT)) {
				Comment comment = new Comment(r);
				comment.setUser(userCache.computeIfAbsent(r.getUserCode(), k -> userMapper.getUserSimpleInfo(r.getUserCode())));
				tv.addComment(comment);
			}
			if (r.getType().equals(TaskAttrType.ATTACHMENT)) {
				Attachment attachment = new Attachment(r);
				attachment.setUser(userCache.computeIfAbsent(r.getUserCode(), k -> userMapper.getUserSimpleInfo(r.getUserCode())));
				tv.addAttachment(attachment);
			}
		});
	}

	public Timestamp addAttechment(String userCode, Integer id, String fileName, String filePath) {
		Timestamp createTs = new Timestamp(System.currentTimeMillis());
		TaskAttr attr = new TaskAttr();
		attr.setCreateTs(createTs);
		attr.setType(TaskAttrType.ATTACHMENT);
		attr.setTaskId(id);
		attr.setUserCode(userCode);
		JsonObject value = new JsonObject();
		value.addProperty("fileName", fileName);
		value.addProperty("filePath", filePath);
		attr.setValue(value.toString());
		taskAttrMapper.createTaskAttr(attr);
		return createTs;
	}

	public Timestamp createComment(String userCode, Integer id, String comment) {
		Timestamp createTs = new Timestamp(System.currentTimeMillis());
		TaskAttr attr = new TaskAttr();
		attr.setTaskId(id);
		attr.setCreateTs(createTs);
		attr.setUserCode(userCode);
		attr.setValue(comment);
		attr.setType(TaskAttrType.COMMENT);
		taskAttrMapper.createTaskAttr(attr);
		return createTs;
	}
}
