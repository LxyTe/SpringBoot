package com.dist.service2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dist.entity.UserEntity;
import com.dist.entity.UserEntity2;
import com.dist.service.mapping.User1Mapping;
import com.dist.service2.mapping2.User2Mapping;

@Service
public class User2Service  implements User2Mapping{

	
	@Autowired
	private User2Mapping User2Mapping;
	

	@Override
	public List<UserEntity2> findA() {
	  List<UserEntity2>entity2s=User2Mapping.findA();
		return entity2s;
	}


	@Override
	public void AddUser(int uid, String birthday, String date, String homeaddress, String password, String sex,
			String username) {
		
		User2Mapping.AddUser(uid, birthday, date, homeaddress, password, sex, username);
		
	}
}
