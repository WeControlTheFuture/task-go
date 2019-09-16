package com.wctf.task.go.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wctf.task.go.model.User;

@Mapper
public interface OrgnizationMapper {

	@Select(value = { "select o.code as code,u.name as name,u.headpic as headpic from orgnization as o left join user as u on o.code = u.code  where o.boss = #{boss}" })
	List<User> getMembers(@Param("boss") String boss);

	List<User> getSelfAndSiblings();
}
