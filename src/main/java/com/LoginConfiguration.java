package com;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
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
        // 排除资源请求
        loginRegistry.excludePathPatterns("/static/**");
        // 排除登录登出路径
        loginRegistry.excludePathPatterns("/","/user/**","/head","/page/square");
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("3MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("10240KB");
        return factory.createMultipartConfig();
    }
}