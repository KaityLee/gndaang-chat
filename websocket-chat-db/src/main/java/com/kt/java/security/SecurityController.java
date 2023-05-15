package com.kt.java.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/form") 		// 로그인 폼 호출
	public String loginForm()
	{
		return "board/login";
	}
	
	@GetMapping("/loginsuccess")
	public String success(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,HttpSession session,Model m)
	{
		Map<String,Object> map = new HashMap<>();
		Collection<GrantedAuthority> col = user.getAuthorities();
		List<GrantedAuthority> list = new ArrayList(List.of(col.toArray()));
		String role = list.get(0).toString();

		System.out.println(role);
		switch (role) {
			case "ROLE_USER": {
				String username = user.getUsername();
				session.setAttribute("username", username);
				map.put("confirm",true);
			}
		}
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/register")
	public String registerForm()
	{
		return "board/RegisterForm";
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public Map<String,Object> signup(Users user)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("success", svc.addUsers(user));
		return map;
	}
	
}
