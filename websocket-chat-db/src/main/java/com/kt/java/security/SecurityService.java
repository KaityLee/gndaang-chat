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
	public boolean addUsers(Users user) 
	{	
		
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		user.setPassword(enc.encode(user.getPassword()));
		
		dao.addUser(user);	
		
		int rows = dao.addAuthorities(user);
		return rows>1;
	}
	
	// id 중복 체크
	public boolean vidCheck(String vid) 
	{
		List<Users> list = dao.uidCheck(vid);
		return list.size()>0;
	}

}
