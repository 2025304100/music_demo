package com.music_demo.file_model.file.path_file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.file.file")
public class Path_ {
    public void setPath(String path) {
        this.path = path.substring(1);

    }

    public String getPath() {
        return path;
    }

    private String path;

    @Bean("path")
    public String Path() {
        return this.path;
    }
}
