package com.music_demo.path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "music.host.server")
public class Host {
    private String Host;

    public void setHost(String host) {
        Host = host;
    }

    public String getHost() {
        return Host;
    }
}
