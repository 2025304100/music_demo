package com.music_demo.inform.entrance.imp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "information.role")
public class announce {
    private Integer role_type;
    private String role;
    private String username;

    public void setRole_type(Integer role_type) {
        this.role_type = role_type;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole_type() {
        return role_type;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public informEmail infoInit() {
        return new informEmail();
    }
}
