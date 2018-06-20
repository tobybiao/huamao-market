package com.example.huamao.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.nio.charset.StandardCharsets;

/** 配置DispatcherServlet
 * @author toby tobytb@163.com
 * @date 2018/5/1 20:46
 */
public class MarketWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class, MongoConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class }; // 指定配置类
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // 将DispatcherServlet 映射到 "/"
    }
}
