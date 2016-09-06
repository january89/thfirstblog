package com.index.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by eight on 2016-09-05.
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  public void configure(WebSecurity http) throws Exception {
    http
        .ignoring()
        .antMatchers("/","/index","/assets/**","/login");

  }
  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http
        .authorizeRequests()
        .antMatchers("/admin/**")
        .access("ROLE_ADMIN");

    http
        .formLogin()
        .usernameParameter("eMail")
        .passwordParameter("password")
        .loginPage("/login")
        .successForwardUrl("/index");

    http
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/index");
  }
}
