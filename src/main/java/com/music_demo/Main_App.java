package com.music_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.music_demo.mapper.dao")
//public class Main_App extends SpringBootServletInitializer
public class Main_App {
    public static void main(String[] args) {
        SpringApplication.run(Main_App.class, args);
//        SpringApplication appilcation=new SpringApplication(Main_App.class);
//        appilcation.run(args);

    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return super.configure(builder);
//    }
}
