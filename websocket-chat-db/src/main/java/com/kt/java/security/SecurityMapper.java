package com.kt.java.security;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecurityMapper {
	public int addUser(Users user);
	public List<Users> uidCheck(String uid);
	public int addAuthorities(Users user);

}
