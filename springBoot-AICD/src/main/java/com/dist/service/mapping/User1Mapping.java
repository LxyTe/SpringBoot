package com.dist.service.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.dist.entity.UserEntity;

@Service
public interface User1Mapping {

	
	@Select("select * from tt")
	public List<UserEntity> findAll();
	
	@Insert("insert into tt (id ,name)  values (#{id} ,#{name})")
	void addTT(@Param("id") int id,@Param("name")String name);
	
	
}
