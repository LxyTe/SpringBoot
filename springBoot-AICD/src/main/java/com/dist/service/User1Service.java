package com.dist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dist.entity.UserEntity;
import com.dist.service.mapping.User1Mapping;

@Service
public class User1Service {

	
	@Autowired
	private User1Mapping user1Mapping;
	
	public List<UserEntity> findAll(){
		System.err.println("异步进入查询操作");
		return user1Mapping.findAll();
	}
	
	public void addTT( int id , String name){
		user1Mapping.addTT(id, name);
	}
	
	@Async
	public void async(){
		System.out.println("正在执行.....循环");
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("i--------------"+i);
		}
		System.out.println("循环执行完毕......");
	}
}
