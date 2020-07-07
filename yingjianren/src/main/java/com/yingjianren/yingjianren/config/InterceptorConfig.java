package com.yingjianren.yingjianren.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {// 登录校验
        //配置这里可以修改不需要登录的页面
        //List<String> excludePaths = Arrays.asList("/Identity/Account/Login", "/Identity/Account/Register","/","/search");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/selfspace");/*.excludePathPatterns(excludePaths).
                excludePathPatterns("/3d/**").excludePathPatterns("/bootstrap-4.5.0-dist/**").excludePathPatterns("/css/**").
                excludePathPatterns("/fontawesome-free-5.11.2-web/**").excludePathPatterns("/icon/**").excludePathPatterns("/img/**").
                excludePathPatterns("/jquery_jBox/**").excludePathPatterns("/js/**").excludePathPatterns("/list/**").
                excludePathPatterns("/magnific-popup/**").excludePathPatterns("/myheart/**").excludePathPatterns("/Radio/**").
                excludePathPatterns("/slick/**").excludePathPatterns("/timeAxis/**");*/
    }
}
