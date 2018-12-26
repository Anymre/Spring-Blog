package com.connext.spring_security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author: Marcus
 * @Date: 2018/12/22 11:01
 * @Version 1.0
 */
@Configuration
public class WebPathConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/reg").setViewName("reg");
        registry.addViewController("/login").setViewName("login");
    }
}

