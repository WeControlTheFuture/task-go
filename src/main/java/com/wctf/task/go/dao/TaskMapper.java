package com.wctf.task.go.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.wctf.task.go.model.CreateTaskParam;
import com.wctf.task.go.model.Task;
import com.wctf.task.go.model.TaskStatus;

@Mapper
public interface TaskMapper {

	@Insert(value = { "insert into task(creater,assignee,title,description,stop_date) value(#{creater},#{assignTo},#{taskTitle},#{description},#{stopDate})" })
	public void create(CreateTaskParam taskParam);

	@Select(value = { "<script>" + "select id,title,stop_date from task where" + "<if test='assignee!= null '>" + " assignee = #{assignee}" + "</if><if test='assignee== null '>assignee is null</if>"
			+ " and status not in <foreach item='item' collection='status' open='(' separator=',' close=')'>  #{item}</foreach></script>" })
	@Results({ @Result(property = "stopDate", column = "stop_date") })
	public List<Task> getTaskExcludeStatus(@Param("assignee") String assignee, @Param("status") List<TaskStatus> status);

	@Select(value = { "<script>" + "select id,title,stop_date,assignee,status from task where  assignee in <foreach item='item' collection='assignees' open='(' separator=',' close=')'>  #{item}</foreach>"
			+ " and status != #{status} </script>" })
	public List<Task> getTasksExcludeStatus(@Param("assignees") List<String> assignees, @Param("status") TaskStatus status);

	@Select(value = { "select * from task where id = #{id}" })
	@Results({ @Result(property = "createTs", column = "create_ts"), @Result(property = "stopDate", column = "stop_date") })
	public Task getTaskById(@Param("id") int id);
}
