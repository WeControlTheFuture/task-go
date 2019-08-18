package com.wctf.task.go.security;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.wctf.task.go.dao.UserMapper;
import com.wctf.task.go.model.User;

@Component
public class MysqlUserDetailsService implements UserDetailsService, UserDetailsPasswordService {
	private static final Log logger = LogFactory.getLog(MysqlUserDetailsService.class);
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.getUserByCode(username);
		if (user == null) {
			logger.error("can't find any user for username " + username + ", login may failed");
			return null;
		}
		return new org.springframework.security.core.userdetails.User(username, encoder.encode(user.getPassword()), Arrays.asList(new SimpleGrantedAuthority("ROLE_"+user.getRole())));
	}

	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		// TODO: add this function later
		return null;
	}

}
