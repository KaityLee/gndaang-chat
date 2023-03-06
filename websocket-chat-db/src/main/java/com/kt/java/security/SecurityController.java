package com.kt.java.security;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class SecurityController {

	@Autowired
	private SecurityService svc;
	
	@Autowired
	private HttpSession session;

	@GetMapping("/") 		// 로그인 폼 호출
	public String loginForm()
	{
		return "board/login";
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public boolean signup(Users user)
	{
		return svc.addUsers(user)>1;
	}
	
}
