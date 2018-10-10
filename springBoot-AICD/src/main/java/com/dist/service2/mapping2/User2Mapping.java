package com.dist.service2.mapping2;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dist.entity.UserEntity2;

@Service
@CacheConfig(cacheNames="baseCache")  //表示引用哪个缓存，ecache缓存的东西都放在jvm内存中，重启就丢失了
public interface User2Mapping {

	@Select("select * from user")
	@Cacheable //将查询的数据，缓存在内存中
	public List<UserEntity2>findA();
	
	@Insert("insert into user (uid , birthday , date , homeaddress , password , sex,username ) values (#{uid}, #{birthday} , #{date} , #{homeaddress} , #{password} , #{sex} , #{username})")
	void AddUser(@Param("uid") int uid , @Param("birthday") String  birthday, @Param("date") String date, @Param("homeaddress") String homeaddress ,@Param("password") String password , @Param("sex") String sex ,@Param("username") String username);
	
}
