package com.hyh.cstore.config;

import com.hyh.cstore.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {


    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/web/login.html",
                        "/web/register.html",
                        "/users/login",
                        "/users/reg",
                        "/districts/**",
                        "/products/**"
                        );
    }
}
