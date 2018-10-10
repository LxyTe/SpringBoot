package com.dist.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dist.dbconfig1.DBConfig1;
import com.dist.dbconfig1.DBConfig2;


@SpringBootApplication
@MapperScan(basePackages="com.dist.service")
@ComponentScan(basePackages="com.dist")
//@EnableCaching  //开启缓存注解
//@EnableScheduling //启动扫描注解配置
@EnableAsync //开启异步
@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class })  //加载具体的属性配置文件内容
public class SpringBootAicdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAicdApplication.class, args);
	}
}
