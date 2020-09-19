package com.music_demo.mapper.dao;

import com.music_demo.entity.Comment;
import com.music_demo.entity.loginMusic;
import com.music_demo.entity.music;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.List;

@Mapper
public interface Resource {
    //获取列表数
    @Select("select count(`id`) from `music` ")
    int getCount();

    int music_red(music min);

    int music_redpa(music min);

    //获取封面音乐  --存入缓存里   -组列表
    //以下未优化
    @Select("select * from `music` order by ${key} desc  limit #{cont} ")
    List<music> get_Music(String key, int cont);
    //翻页查询;

    @Select("select * from `music` ${where} order by #{key} desc limit #{cont},#{size} ")
    List<music> get_Music_dms(String key, int cont, int size, String where);

    //获取登录信息
    List<music> getUserMusic(String key, int cont, int size, String where, String username);

    @Select("select * from `music`   order by #{key} desc limit #{cont},#{size} ")
    List<music> get_Music_dm(String key, int cont, int size);

    //模糊查询
    @Select("select * from `music` where concat(IFNULL(`music_name`,''),IFNULL(`file_music_author_name`,'')) LIKE `%#{key}%` ")
    List<music> get_MusicLike(String key);

    @Select("select * from `music` where id=#{id}")
    music get_Musicind(@Param("id") long id);

    //获取最新时间
    @Select("select MAX(`music_data`) from `music` ")
    Date getNewDate();

    List<Comment> getComent(long music_id);

    int setComent(Comment us);

}
