package com.wctf.task.go.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wctf.task.go.security.LoginSuccessHandler;
import com.wctf.task.go.security.MysqlUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MysqlUserDetailsService mysqlUserDetailsService;

	/**
	 * 配置用户认证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(mysqlUserDetailsService);
		authProvider.setUserDetailsPasswordService(mysqlUserDetailsService);
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/peform_login").successHandler(new LoginSuccessHandler()).permitAll().and()
				.logout().permitAll().and().csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/bower_components/**", "/dist/**", "/js/**", "/css/**", "/icons/**", "/plugins/**", "/profile/**", "/favicon.ico");
	}

}
