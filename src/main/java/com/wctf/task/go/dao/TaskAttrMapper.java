package com.wctf.task.go.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.wctf.task.go.model.TaskAttr;

@Mapper
public interface TaskAttrMapper {

	@Insert(value = { "insert into task_attr(create_ts,task_id,type,value,user_code) value(#{createTs},#{taskId},#{type},#{value},#{userCode})" })
	public int createTaskAttr(TaskAttr taskAttr);

	@Select(value = { "select * from task_attr where task_id=#{taskId} order by create_ts asc" })
	@Results({ @Result(column = "task_id", property = "taskId"), @Result(column = "create_ts", property = "createTs"), @Result(column = "type", property = "type"),
			@Result(column = "value", property = "value"), @Result(column = "user_code", property = "userCode") })
	public List<TaskAttr> getTaskAttrByTaskId(@Param("taskId") Integer taskId);
}
