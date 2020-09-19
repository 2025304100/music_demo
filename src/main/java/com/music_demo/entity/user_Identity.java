package com.music_demo.entity;

import org.apache.ibatis.annotations.Insert;

import java.io.Serializable;

public class user_Identity implements Serializable {
    private Integer id;
    //身份等级
    private Integer identity;
    //vip有效期
    private Integer indentity_data;
    //用户等级
    private Integer grade;
    //是否违法
    private Integer is_of;

    @Override
    public String toString() {
        return "user_Identity{" +
                "id=" + id +
                ", identity=" + identity +
                ", indentity_data=" + indentity_data +
                ", grade=" + grade +
                ", is_of=" + is_of +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public void setIndentity_data(Integer indentity_data) {
        this.indentity_data = indentity_data;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setIs_of(Integer is_of) {
        this.is_of = is_of;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdentity() {
        return identity;
    }

    public Integer getIndentity_data() {
        return indentity_data;
    }

    public Integer getGrade() {
        return grade;
    }

    public Integer getIs_of() {
        return is_of;
    }
}
