package com.music_demo.path;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
@ConfigurationProperties(prefix = "music.view")
public class isMonitoring {
    public java.lang.String getIs() {
        return is;
    }

    public void setIs(java.lang.String is) {
        this.is = is;
    }

    private  String is;
}
