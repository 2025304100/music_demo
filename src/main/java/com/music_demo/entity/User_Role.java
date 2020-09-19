package com.music_demo.entity;

public class User_Role {
    private Integer id;
    //多用户以#隔开
    private String role;

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User_Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
