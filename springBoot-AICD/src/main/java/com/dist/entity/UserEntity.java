package com.dist.entity;

import java.io.Serializable;

/**
 * 
 * @author 刘新杨
 *   菩提本无树，
 *   明镜亦非台。
 *   对应 数据源一中的某张表
 */


public class UserEntity implements Serializable{

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + "]";
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int id;

	private String name;
	
}
