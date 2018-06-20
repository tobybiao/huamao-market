package com.example.huamao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/1 21:02
 */
@Configuration
@ComponentScan(basePackages = {"com.example.huamao"},
        excludeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
@PropertySource(value = "classpath:config.properties")
public class RootConfig {
}
