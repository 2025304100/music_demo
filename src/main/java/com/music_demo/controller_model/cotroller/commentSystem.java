package com.music_demo.controller_model.cotroller;

import com.music_demo.entity.Comment;
import com.music_demo.entity.User_entity;
import com.music_demo.entity.music_des;
import com.music_demo.redis_model.User_Redis;
import com.music_demo.security_module.encryption_model.Base64;
import com.music_demo.service_model.User;
import com.music_demo.service_model.music2.proxy.Proxy;
import com.music_demo.service_model.music2.service.commentImp;
import com.music_demo.service_model.music2.service.offcer.comment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class commentSystem {
    /**
     * 获取评论
     */
    @Autowired
    private commentImp com;
    //认证之后才能获取
    @Autowired
    User_Redis rds;
    @Autowired
    User user;
    private Logger log = Logger.getLogger(commentSystem.class);

    @RequestMapping("user_comment")
    public List<Comment> getComment(HttpSession ses) throws UnsupportedEncodingException {
        /**
         * @demo:com.music_demo.controller_model.music_Index
         * 必须经过跳转获取跳转
         */
        music_des ds = ((music_des) ses.getAttribute("music_des"));
        String ms = ds.getIdpas();
        Long music_id = Long.valueOf(new String(Base64.decode(ms.getBytes()), "utf-8"));
        if (music_id == null) {
            throw new NullPointerException(commentSystem.class.getName() + "music_id为空");
        }
        Proxy music = new Proxy(com);
        comment o = (comment) java.lang.reflect.Proxy.newProxyInstance(comment.class.getClassLoader(), new Class[]{comment.class}, music);
        List<Comment> comment = o.getComment(music_id);
        return comment;
    }

    //从缓存获取
    @RequestMapping("sent_comment")
    public String setComment(String profile_text, String after_review, String afer_name, String music_id, HttpServletRequest req, HttpSession ses) throws UnsupportedEncodingException {
        String username = (String) ses.getAttribute("username");
        System.out.println(req.getParameter("music_id"));
        //载入数据
        System.out.println("username" + profile_text + "musicId" + music_id + "username" + username);
        if (music_id == null || username == null)
            throw new NullPointerException(commentSystem.class.getName() + "music_id为空或获取username为空(null)");

        Comment com = new Comment();
        com.setAfter_name(afer_name);
        com.setAfter_review(after_review);
        com.setMusic_id(Long.valueOf(new String(Base64.decode(music_id.getBytes()), "utf-8")));
        com.setProfile_text(profile_text);
        com.setProfileint(username);
        Proxy proxy = new Proxy(this.com);
        comment comment = (comment) java.lang.reflect.Proxy.newProxyInstance(comment.class.getClassLoader(), new Class[]{comment.class}, proxy);
        //发送评论
        boolean b = comment.setComment(com);
        String state = b ? "发表成功" : "发表失败";
        return state;
    }
}
