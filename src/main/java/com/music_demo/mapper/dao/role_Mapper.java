package com.music_demo.mapper.dao;

import com.music_demo.entity.collect_en;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface role_Mapper {
    Long collect(collect_en ms);

    Integer setConllect(collect_en ms);

    //查询收藏
    @Select("SELECT `id` FROM `user_music` WHERE `username`=#{username} AND `music_id`=#{music_id} ")
    Integer slectCollect(String username, Long music_id);

    //删除收藏
    @Delete("DELETE FROM `user_music` WHERE `username`=#{username} AND `music_id`=#{music_id} ")
    Integer removeCollect(String username, Long music_id);
}
