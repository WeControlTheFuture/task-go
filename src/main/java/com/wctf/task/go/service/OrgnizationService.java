package com.wctf.task.go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wctf.task.go.dao.OrgnizationMapper;
import com.wctf.task.go.model.User;

@Service
public class OrgnizationService {

	@Autowired
	private OrgnizationMapper orgnizationMapper;
	
	public List<User> getMembers(String boss){
		return orgnizationMapper.getMembers(boss);
	}
}
