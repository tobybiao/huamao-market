package com.example.huamao.controller;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.pojo.LoginForm;
import com.example.huamao.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/8 10:29
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value="/user/login", method= RequestMethod.POST)
    @ResponseBody
    public GenericResult login(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        String usrname = "";
        try {
            usrname = new String(loginForm.getUsername().getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("controller 收到视图传来的数据：" + "用名：" + usrname + "；密码：" + "不应该显示" + "；用户类型： " + loginForm.getUserType());
        return this.loginService.login(loginForm.getUsername(), loginForm.getPassword(), loginForm.getUserType(), request, response);
    }

    /**
     * 用户退出登录
     * @return GenericResult
     */
    @RequestMapping(value="/user/logout", method= RequestMethod.POST)
    @ResponseBody
    public GenericResult logout(HttpServletRequest request) {
        return this.loginService.logout(request);
    }
}
