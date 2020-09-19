package com.music_demo.entity;

public class loginMusic_des {
    private String idpas;
    private String file_img;
    //哥名
    private String music_name;
    //作者

    private String music_author_name;
    private String file_lyirc;
    //音乐类型 0-普通  1-vip  3-专卖
    private Integer music_index;
    private Integer contxte;
    private String file_music;

    private String username;

    private Boolean islogin;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIslogin(Boolean islogin) {
        this.islogin = islogin;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getIslogin() {
        return islogin;
    }

    public void setIdpas(String idpas) {
        this.idpas = idpas;
    }


    public void setFile_img(String file_img) {
        this.file_img = file_img;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public void setMusic_author_name(String music_author_name) {
        this.music_author_name = music_author_name;
    }

    public void setFile_lyirc(String file_lyirc) {
        this.file_lyirc = file_lyirc;
    }

    public void setMusic_index(Integer music_index) {
        this.music_index = music_index;
    }

    public void setContxte(Integer contxte) {
        this.contxte = contxte;
    }

    public void setFile_music(String file_music) {
        this.file_music = file_music;
    }

    public String getMusic_name() {
        return music_name;
    }

    public String getMusic_author_name() {
        return music_author_name;
    }

    public String getFile_lyirc() {
        return file_lyirc;
    }

    public Integer getMusic_index() {
        return music_index;
    }

    public Integer getContxte() {
        return contxte;
    }

    public String getFile_music() {
        return file_music;
    }

    public String getFile_img() {
        return file_img;
    }

    public String getIdpas() {
        return idpas;
    }
}