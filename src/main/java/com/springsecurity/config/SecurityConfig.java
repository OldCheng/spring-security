package com.springsecurity.config;

import com.springsecurity.handler.MyAccessDeniedHandler;
import com.springsecurity.handler.MyAuthenticationFailureHandler;
import com.springsecurity.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

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
                // 自定义登录成功处理器
                //.successHandler(new MyAuthenticationSuccessHandler("http://www.baidu.com"))
                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                //.failureForwardUrl("/toError");
                // 自定义登录失败处理器
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));
        //授权
        http.authorizeRequests()
                // 没有登录成功也要放行 执行/error.html 不需要认证
                .antMatchers("/error.html").permitAll()
                // 执行/login.html 不需要认证
                .antMatchers("/login.html").permitAll()
                // 放行静态资源
                .antMatchers("/css/**", "/images/**", "/js/**").permitAll()
//                .antMatchers("/**/*.jpg").permitAll()
                // 放行后缀 .jpg ,正则表达式
                //.regexMatchers(".+[.]jpg").permitAll()
                // 指定请求方法
                //.regexMatchers(HttpMethod.POST,".+[.]jpg").permitAll()
//                .regexMatchers(".+[.]jpg").permitAll()
//                .regexMatchers("/demo").permitAll()
                // 权限控制，严格区分大小写
                // 基于权限
//                .antMatchers("/main1.html").hasAuthority("admin")
//                .antMatchers("/main1.html").hasAnyAuthority("admin","admiN")
                // 基于角色
//                .antMatchers("/main1.html").hasRole("abC")
                //.antMatchers("/main1.html").hasAnyRole("abC", "abc")
                // 基于 IP地址
                .antMatchers("/main1.html").hasIpAddress("127.0.0.1")
                // 所有请求都必须认证才能访问，必须登录
                .anyRequest().authenticated();
        // 异常处理
        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);

        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }
}
