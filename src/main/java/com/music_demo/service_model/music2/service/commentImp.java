package com.music_demo.service_model.music2.service;

import com.music_demo.entity.Comment;
import com.music_demo.entity.User_entity;
import com.music_demo.mapper.dao.Resource;
import com.music_demo.mapper.dao.UserMapper;
import com.music_demo.redis_model.User_Redis;
import com.music_demo.service_model.User;
import com.music_demo.service_model.music2.background.commentBackground;
import com.music_demo.service_model.music2.service.offcer.comment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class commentImp implements comment {
    @Autowired
    Resource reso;

    //认证之后才能获取(缓存得出)
    @Autowired
    User_Redis rds;
    //用于在查询
    @Autowired
    User user;
    @Autowired
    UserMapper userMapper;
    private Logger log = Logger.getLogger(commentImp.class);

    commentImp() {

    }

    @Override
    public List<Comment> getComment(Long id) {
        List<Comment> coment = reso.getComent(id);
        if (coment == null) {
            log.error(comment.class.getClassLoader() + "查询为空");
            coment = new ArrayList<>();
        }
        return coment;
    }

    @Override
    public boolean setComment(Comment com) {
        String username = com.getProfileint();
        System.out.println(username);
        User_entity user = rds.getUser(username);
        String file_img = user.getFile_img();
        String pror_name = user.getName();
        com.setPror_name(pror_name);
        com.setFile_img(file_img);
        new Thread(() -> commentBackground.Production(com, reso, userMapper)
        ).start();
        return true;
    }

}

