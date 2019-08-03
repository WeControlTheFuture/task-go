package com.wctf.task.go.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wctf.task.go.model.User;

@Mapper
public interface UserMapper {

	@Select("select * from user where code=#{code}")
	public User getUserByCode(@Param("code") String code);

	@Select("<script>select name,code,headpic from user where code in <foreach item='item' collection='codes' open='(' separator=',' close=')'> #{item} </foreach></script>")
	public List<User> getUserSimpleInfos(@Param("codes") Set<String> code);

	@Select("select name,code,headpic from user where code=#{code}")
	public User getUserSimpleInfo(@Param("code") String code);
}
