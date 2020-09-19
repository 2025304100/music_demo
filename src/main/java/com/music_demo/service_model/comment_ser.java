package com.music_demo.service_model;

import com.music_demo.entity.Comment;
import com.music_demo.mapper.dao.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class comment_ser {
    @Autowired
    Resource rs;

    //获取用户
    public List<Comment> getComment(long music_id) {

        List<Comment> lis = null;
        lis = rs.getComent(music_id);
        if (lis == null) {
            lis = new ArrayList<>();
        }
        return lis;
    }

    public boolean setComment(Comment mx) {
        int i = rs.setComent(mx);
        if (i == 1)
            return true;
        return false;
    }
}
