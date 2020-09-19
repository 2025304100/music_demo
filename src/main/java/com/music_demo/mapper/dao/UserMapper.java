package com.music_demo.mapper.dao;


import com.music_demo.entity.User_Role;
import com.music_demo.entity.User_entity;
import com.music_demo.entity.user_Identity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface UserMapper {
    //注册用基本信息
    int Register(User_entity user_entity);

    //   注册用户特色信息
    int Identity(user_Identity identity);

    //注册用户角色--默认user
    int user_Role(User_Role user_role);

    //获取用户的特殊.
    user_Identity getIdentity(Long id);

    //获取用户角色
    User_Role getRole(Long id);

    //账户查重
    String getUsername(String username);

    //邮箱才从
    String getEmail(String email);

    //账户登录
    @Select("select * from user_ where username=#{username}")
    User_entity login_User(String username);

    //用户模拟多表数据

    //用户id查询
    @Select("select id from user_ where username=#{username}")
    Long getUser_id(String username);

    //多用户查询
    @Select("select ${titles} from user_identity where id=#{id}")
    Object getUser(String titles, Long id);

}
