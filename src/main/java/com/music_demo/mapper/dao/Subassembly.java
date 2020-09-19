package com.music_demo.mapper.dao;

import com.music_demo.entity.musicType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Subassembly {
    //获取全部类型
    @Select("select * from `mtype`")
    public List<musicType> getmusicType();

}
