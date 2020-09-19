package com.music_demo.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
public class DdS {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    App_demo demo() {
        return new App_demo();
    }
}
