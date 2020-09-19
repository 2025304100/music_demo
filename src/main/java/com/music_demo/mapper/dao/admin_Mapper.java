package com.music_demo.mapper.dao;

import com.music_demo.entity.user_Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface admin_Mapper {
    //创建角色
    @Insert("insert into admin_user values(default,username,pas,role,role_type)")
    public int add_Role(user_Admin usr);

    //根据权限级别获取角色信息
    @Select("select * form admin_user where `role_type`>#{role_type}")
    List<user_Admin> getAllType(String role_type);

    //根据角色获取信息
    @Select("select `id`,`username`,`pas`,`role`,`role_type` form `admin_user` where `role`=#{role}")
    List<user_Admin> getAllRole_user(String role);

    //根据角色获取角色信息
    @Select("select `id`,`username`,`pas`,`role`,`role_type` form `admin_user` where `username`=#{username} ")
    user_Admin getOneUser(String username);

}
