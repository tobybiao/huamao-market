package com.example.huamao.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.common.pojo.ISessionKey;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author toby tobytb@163.com
 * @date 2018/6/4 20:12
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        if(requestURI.contains("/user/login")) {
            return true;
        }
        Object loginData = httpServletRequest.getSession().getAttribute(ISessionKey.USER_INFO);
        if(null == loginData) {
            GenericResult result = new GenericResult();
            result.setData(null);
            result.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("未登录，无权限访问资源");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().append(JSON.toJSONString(result));
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
