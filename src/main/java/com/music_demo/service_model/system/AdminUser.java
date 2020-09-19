package com.music_demo.service_model.system;

import com.music_demo.entity.user_Admin;
import com.music_demo.mapper.dao.admin_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUser {
    @Autowired
    private admin_Mapper map;

    /**
     * @param role_type
     * @return
     * @effect:根据权限级别获取角色信息
     */
    public List<user_Admin> getAllType(String role_type) {
        List<user_Admin> allType = map.getAllType(role_type);
        if (allType == null) {
            allType = new ArrayList<>();
        }
        return allType;
    }

    /**
     * @param role
     * @return
     * @effect:根据权限级别获取角色信息
     */
    public List<user_Admin> getAllRole_user(String role) {
        List<user_Admin> allType = map.getAllRole_user(role);
        if (allType == null) {
            allType = new ArrayList<>();
        }
        return allType;
    }

    public user_Admin getOneUser(String username) {
        user_Admin oneUser = map.getOneUser(username);
        if (oneUser == null) {
            oneUser = new user_Admin();
        }
        return oneUser;
    }
}


