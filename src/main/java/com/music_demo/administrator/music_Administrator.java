package com.music_demo.administrator;

import com.music_demo.administrator.excute.excute_Music;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class music_Administrator {
    @Bean(initMethod = "init", destroyMethod = "destory")
    public excute_Music Redis_excute() {
        return new excute_Music();
    }

}
