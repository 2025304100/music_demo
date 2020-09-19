package com.music_demo.admin_resu;

import com.music_demo.monitoring.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Admin {
    //挂载计算机资源的监听器
    @Bean(initMethod = "init")
    public Resource Resource_pc() {
        Resource resource = new Resource();

        return resource;
    }
}
