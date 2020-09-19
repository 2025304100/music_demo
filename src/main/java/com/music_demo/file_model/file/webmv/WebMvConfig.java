package com.music_demo.file_model.file.webmv;

import com.music_demo.file_model.file.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//一种全局配置
@Configuration
public class WebMvConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new user_file())
                .addPathPatterns("/user/**");
        registry.addInterceptor(new admin_file())
                .addPathPatterns("/admin/**");
        registry.addInterceptor(new resource_file())
                .addPathPatterns("/resource_file/**");
        registry.addInterceptor(new music_file())
                .addPathPatterns("/music/**");
        registry.addInterceptor(new interceptor_As())
                .addPathPatterns("/role/**");
        registry.addInterceptor(new commentFile())
                .addPathPatterns("/commentFile/**");

    }
}
