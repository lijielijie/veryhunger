package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.interceptor.LoginInterceptor;

@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
	// 注册拦截器
	@Autowired
    private LoginInterceptor loginInterceptor ;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        //loginRegistry.addPathPatterns("/**");
        // 排除登录登出路径
        loginRegistry.excludePathPatterns("/","/user/**");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/static/**");
    }
}