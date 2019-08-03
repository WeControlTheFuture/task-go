package com.wctf.task.go.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wctf.task.go.model.TaskAttr;

@Mapper
public interface TaskAttrMapper {

	@Insert(value = { "insert into task_attr(task_id,type,value,user_code) value(#{taskId},#{value},#{type},#{userCode})" })
	public int createTaskAttr(TaskAttr taskAttr);

	@Select(value = { "select * from task_attr where task_id=#{taskId} order by create_ts desc" })
	public List<TaskAttr> getTaskAttrByTaskId(@Param("taskId") Integer taskId);
}
