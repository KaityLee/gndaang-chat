package com.kt.java.security;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import jakarta.servlet.http.HttpSession;

@Service
public class SecurityService {
	
	@Autowired
	private SecurityMapper dao;
	
	
 	@Transactional	// DB에 vendor 추가
	public int addUsers(Users user) 
	{
		dao.addUser(user);		
		
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		user.setPassword(enc.encode(user.getPassword()));
		
		int rows = dao.addAuthorities(user);
		return rows;
	}
	
	// id 중복 체크
	public boolean vidCheck(String vid) 
	{
		List<Users> list = dao.uidCheck(vid);
		return list.size()>0;
	}

}
