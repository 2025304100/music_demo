package com.music_demo.controller_model;

import com.music_demo.admin_resu.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("manage")
public class Admin_rsouic {
    @Autowired
    Admin admin;

    //hardware resources(硬件资源监听器）
    @RequestMapping("/hardware")
    public @ResponseBody
    Map<String, String> hardwareRwaources(HttpSession ses) {
        Map<String, String> map;
        map = admin.Resource_pc().get_Huoqu();
        if (map == null)
            map = new HashMap<>();

        return map;
    }
}
