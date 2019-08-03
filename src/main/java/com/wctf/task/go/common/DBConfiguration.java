package com.wctf.task.go.common;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wctf.task.go.model.TaskAttrType;
import com.wctf.task.go.model.TaskStatus;

@Configuration
@MapperScan(basePackages = "com.shangceng.account.oa.dao")
public class DBConfiguration {

	@Bean("dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("sqlSessionFactory")
	public SqlSessionFactory mySqlSessionFactory(DataSource dataSource) throws Exception {
		org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();
		registTypeHandlers(conf);
		conf.setJdbcTypeForNull(JdbcType.NULL);
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setConfiguration(conf);
		return bean.getObject();
	}

	private void registTypeHandlers(org.apache.ibatis.session.Configuration conf) {
		conf.getTypeHandlerRegistry().register(TaskStatus.class, new EnumOrdinalTypeHandler<TaskStatus>(TaskStatus.class));
		conf.getTypeHandlerRegistry().register(TaskAttrType.class, new EnumOrdinalTypeHandler<TaskAttrType>(TaskAttrType.class));
	}
}
