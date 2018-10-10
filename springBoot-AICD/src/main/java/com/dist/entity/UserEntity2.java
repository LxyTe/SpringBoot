package com.dist.entity;

import java.io.Serializable;
/**
 * 
 * @author 刘新杨
 *   菩提本无树，
 *   明镜亦非台。
 *   
 *   对应数据源2中某张表
 */
public class UserEntity2 implements Serializable{

	@Override
	public String toString() {
		return "UserEntity2 [uid=" + uid + ", birthday=" + birthday + ", date=" + date + ", homeaddress=" + homeaddress
				+ ", password=" + password + ", sex=" + sex + ", username=" + username + "]";
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHomeaddress() {
		return homeaddress;
	}
	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private int uid;
	
	private String birthday;
	
	private String date;
	
	private String homeaddress;
	private String password;
	private String sex;
	private String username;
	
	
}
