package com.music_demo.mapper.dao;

import com.music_demo.entity.information.PageView;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Message {
    //获取访问量
    public List<PageView> getlPage(@Param("title") String title, @Param("time") int time);

    public PageView onePage(@Param("title") String title, @Param("time") int time);

    public PageView setQuarter(String time, int newtime, int oldtime);

    @Insert("insert into ${title} value(default,#{index},#{pageType.direct},#{pageType.consumer}," +
            "#{pageType.menberPv},#{pageType.superMenberPv},#{visitor_volume},default)")
    public int setPage(PageView pageView, String title);
}
