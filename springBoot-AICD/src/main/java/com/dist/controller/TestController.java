package com.dist.controller;

import java.util.List;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dist.entity.UserEntity;
import com.dist.entity.UserEntity2;
import com.dist.service.User1Service;
import com.dist.service2.User2Service;

@RestController
public class TestController {

	private static Logger log=Logger.getLogger(TestController.class);
	
	@Autowired
	private User1Service user1Service;
	
	@Autowired
	private User2Service User2Service;
	
	@Autowired
	private StringRedisTemplate template;
	
	@RequestMapping("/find")
	public List<UserEntity> findAll(){
	
	     List<UserEntity>  tt=user1Service.findAll();
		return  tt;
	}
	
	@RequestMapping("/findUser")
	public String findA(){
	 //System.out.println("实现热部署");
		if(template.opsForValue().get("tt")!=null){
		System.out.println("从缓存查出数据");
			return template.opsForValue().get("tt");
		}else {
			template.opsForValue().set("tt", User2Service.findA().toString());
		}
   

	 
		return  User2Service.findA().toString();
	}
	@RequestMapping("/addTT/{id}/{name}")
	public String addTT(@PathVariable int id,@PathVariable String name){
		user1Service.addTT(id, name);
		return "success";
	}
	
	@RequestMapping("/addUser/{uid}/{birthday}/{date}/{homeaddress}/{password}/{sex}/{username}")
	public String addUser(@PathVariable int uid,@PathVariable String birthday,@PathVariable String date,@PathVariable String homeaddress, @PathVariable String password, @PathVariable String sex,@PathVariable String username){
	  User2Service.AddUser(uid, birthday, date, homeaddress, password, sex, username);
		return "success";
	}
	
	
	@RequestMapping("/asyncc")
	public void sync (){//测试异步
		System.out.println("准备进入循环");
		user1Service.async();
		System.out.println("异步已出循环");
		
	     System.out.println(user1Service.findAll());
	     
		System.out.println("查询操作已经完成");
	}
}
