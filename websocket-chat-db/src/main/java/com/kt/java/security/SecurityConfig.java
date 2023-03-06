package com.kt.java.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig
{
	@Autowired
	DataSource source;
	
   @Autowired
    DataSource dataSource;    // JDBC Authentication에 필요함

   @Bean
   	  public BCryptPasswordEncoder  passwordEncoder() {
      BCryptPasswordEncoder enc = new BCryptPasswordEncoder(); // 60자로 암호화
       return enc;
   }
   @Autowired
   public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
      log.info("데이터소스 설정");
      auth.jdbcAuthentication().dataSource(dataSource)
      .usersByUsernameQuery(
               "SELECT username,password, enabled FROM users WHERE username=?");
   }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
       return (web) -> web.ignoring().requestMatchers("/resources/**", "/ignore2");
    }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      log.info("접근제한 설정");
      return http.authorizeHttpRequests()/* 권한에 따른 인가(Authorization) */
            .requestMatchers("/board/upload","/board/chatroom"
            		,"/chat").hasAnyRole("USER")//.permitAll()
            .anyRequest().permitAll()        // 위의 설정 이외의 모든 요청은 인증 요구하지 않음
            
            .and()
            .csrf().disable()
            .formLogin().loginPage("/login/")   // 지정된 위치에 로그인 폼이 준비되어야 함
            .loginProcessingUrl("/doLogin")
            // 컨트롤러 메소드 불필요, 폼 action과 일치해야 함 -> form의 action에서 이 url로 보낸다.그래야 로그인 처리를 해준다.
            .failureUrl("/login/")      // 로그인 실패시 다시 로그인 폼으로
            //.failureForwardUrl("/login?error=Y")  //실패시 다른 곳으로 forward
            .permitAll()
            
          .and()   // 디폴트 로그아웃 URL = /logout
          .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃 요청시 URL
          .logoutSuccessUrl("/login/")
          .invalidateHttpSession(true) 
          .deleteCookies("JSESSIONID")
          .permitAll()
          
          .and()
          .exceptionHandling().accessDeniedPage("/sec/denied")
          .and().build();
   }
}