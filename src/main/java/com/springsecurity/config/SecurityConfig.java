package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description
 * @Author 程杰
 * @Date 2021/4/18 16:41
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 自定义登陆页面
                .loginPage("/login.html")
                // 必须和表单提交接口一样,才会执行自定义登录逻辑
                .loginProcessingUrl("/login")
                // 登陆成功跳转到页面
                .successForwardUrl("/toMain")
                .failureForwardUrl("/toError");
        //授权
        http.authorizeRequests()
                // 没有登录成功也要放行
                .antMatchers("/error.html").permitAll()
                // 执行/login.html 不需要认证
                .antMatchers("/login.html").permitAll()
                // 所有请求都必须认证才能访问
                .anyRequest().authenticated();
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder getPw(){
        return new BCryptPasswordEncoder();
    }
}
