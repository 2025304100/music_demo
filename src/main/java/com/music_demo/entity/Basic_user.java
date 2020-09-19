package com.music_demo.entity;

public class Basic_user {
    //用户id
    private Long id;
    //用户名
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(byte index) {
        this.index = index;
    }

    public void setFile_img(String file_img) {
        this.file_img = file_img;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte getIndex() {
        return index;
    }

    public String getFile_img() {
        return file_img;
    }

    //用户等级
    private byte index;

    @Override
    public String toString() {
        return "Basic_user:{" +
                "id:" + id +
                ", name:" + name + '\'' +
                ", index:" + index +
                ", file_img:" + file_img + '\'' +
                '}';
    }

    //y用户名头像路径
    private String file_img;
}
