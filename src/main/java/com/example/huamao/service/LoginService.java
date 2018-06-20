package com.example.huamao.service;

import com.example.huamao.common.pojo.GenericResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author toby tobytb@163.com
 * @date 2018/3/11 15:05
 */
public interface LoginService {
    /**
     * 用户通过用户名、密码、指定用户类型登录
     * @param username 用户名
     * @param password 密码
     * @param userType 用户类型
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return 登录请求结果，成功时候返回token
     */
    GenericResult login(String username, String password, String userType, HttpServletRequest request,
                        HttpServletResponse response);

    /**
     * 通过token 获取用户信息
     * @param token 登录成功返回的token
     * @return 用户信息
     */
    GenericResult getUserByToken(String token);

    /**
     * 用户退出登录
     * @param request HttpServletRequest
     * @return GenericResult
     */
    GenericResult logout(HttpServletRequest request);
}
