package com.springsecurity.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author 程杰
 * @Date 2021/4/19 20:41
 * @Version 1.0
 */
public interface MyService {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
