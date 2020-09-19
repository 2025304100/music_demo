package com.music_demo.entity;

import javax.sql.rowset.spi.SyncResolver;
import java.io.Serializable;

public class user_Admin implements Serializable {
    private Integer id;
    private String username;
    private String pas;
    private String role;
    private Integer role_type;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRole_type(Integer role_type) {
        this.role_type = role_type;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPas() {
        return pas;
    }

    public String getRole() {
        return role;
    }

    public Integer getRole_type() {
        return role_type;
    }
}
