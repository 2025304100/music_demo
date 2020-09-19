package com.music_demo.controller_model;

import com.music_demo.entity.Comment;
import com.music_demo.entity.User_entity;
import com.music_demo.entity.music;
import com.music_demo.entity.music_des;
import com.music_demo.error_model.err.coding;
import com.music_demo.error_model.err.dows_Jurisdiction;
import com.music_demo.offer.user_Offer;
import com.music_demo.path.Host;
import com.music_demo.redis_model.Music;
import com.music_demo.security_module.encryption_model.Base64;
import com.music_demo.service_model.User;
import com.music_demo.service_model.comment_ser;
import com.music_demo.service_model.role_User;
import com.music_demo.service_model.select_music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * 中转获取  -音乐主页面
 */
@Controller
@RequestMapping("/role")
public class Attestation {
    @Autowired
    private role_User ps;
    @Autowired
    Music music;
    @Autowired
    select_music sel;
    @Autowired
    comment_ser ms;
    @Autowired
    User user;
    @Autowired
    user_Offer of;

    @Autowired
    Host path;

    //用户音乐查询
    @GetMapping("/collect/{id}")
    public @ResponseBody
    String isCollect(HttpSession ses, @PathVariable("id") String idpas) throws coding {
        if (!Base64.isBase64(idpas.getBytes()))
            throw new coding();
        String idsrc = new String(Base64.decode(idpas.getBytes()));
        long id = Long.valueOf(idpas);
        String username = (String) ses.getAttribute("username");
        Boolean is = ps.ser_Collect(username, id);
        if (is) {
            return "400";
        } else if (!is) {
            return "401";
        }
        return "402";
    }

    //添加搜查
    @RequestMapping("/add_collect/{id}")
    public @ResponseBody
    String add_Collect(HttpSession sess, @PathVariable("id") String idpas) throws Exception {
        if (!Base64.isBase64(idpas.getBytes()))
            throw new coding();
        String idsrc = new String(Base64.decode(idpas.getBytes()));
        long id = Long.parseLong(idsrc);
        String username = (String) sess.getAttribute("username");
        if (username == null) {
            throw new Exception();
        }
        Integer ids = ps.set_Coller(username, id);
        //成功返回的id号
        if (ids == 1) {
            return "200";
        } else {
            return "500";
        }
    }

    @RequestMapping("/remove_collect/{id}")
    public @ResponseBody
    String remove_Collect(HttpSession ses, @PathVariable("id") String idpas) throws Exception {
        if (!Base64.isBase64(idpas.getBytes()))
            throw new coding();
        String idsrc = new String(Base64.decode(idpas.getBytes()));
        long id = Long.parseLong(idsrc);
        String username = (String) ses.getAttribute("username");
        if (username == null) {
            throw new Exception();
        }
        Integer state = ps.remove_Coller(username, id);
        System.out.println(state);
        if (state == 1)
            return "200";
        else
            return "500";
    }

    @GetMapping("download/{id}")
    public @ResponseBody
    String Downs(HttpSession ses, @PathVariable("id") String id) {
        String username = (String) ses.getAttribute("username");
        Integer in = (Integer) user.userInfo("identity", username);
        music mi = (com.music_demo.entity.music) ses
                .getAttribute(id);
        System.out.println(ses.getId());
        String hors = path.getHost();
        //下载等级
        Integer music_index = mi.getMusic_index();
        String path = hors + "role/dows";
        if (music_index - in > 0) {
            path = (music_index == 2 ? "401" : "400");
            ses.setAttribute("dows", false);
        } else {
            ses.setAttribute("music_path", mi.getFile_music());
            ses.setAttribute("music_name", mi.getMusic_name());
            ses.setAttribute("dows", true);
        }
        return path;
    }

    //资源必须经过跳转接口进行session
    @GetMapping("/dows")
    public void dows(HttpServletResponse response, HttpSession ses) throws IOException, dows_Jurisdiction {
        String audio = null;
        String prus = null;
        String name = null;
        Boolean is = (Boolean) ses.getAttribute("dows");
        if (!is) {
            throw new dows_Jurisdiction();
        }
        audio = (String) ses.getAttribute("music_path");
        name = (String) ses.getAttribute("music_name");
        //音乐类型
        prus = audio.substring(audio.lastIndexOf(".") + 1);

        //目前audio仅支持MP3-ogg wav
        if (prus != "mp3" || prus != "ogg" || prus != "wav") {
            //进行转码 -默认MP3
        }
        File file = new File(audio);
        //设置文件类型
        response.setContentType("");
        response.setHeader("Content-Disposition", "attachment; filename=" + name + "." + prus);
        ServletOutputStream op = response.getOutputStream();
        byte[] i = FileCopyUtils.copyToByteArray(file);
        op.write(i);
        op.close();
    }

    @GetMapping("/discuss")
    public @ResponseBody
    String Discuss(String text, String afer, String afer_name, HttpSession ses, String idpas) throws Exception {
        String username = (String) ses.getAttribute("username");
        music mi = (com.music_demo.entity.music) ses.getAttribute(idpas);
        if (mi == null) {
            return "缺少必要数据";
        }
        Long id = mi.getId();
        if (username == null) {
            return "身份过期!";
        }
        User_entity use = of.user_en(username);
        Comment comment = new Comment();
        comment.setMusic_id(id);
        comment.setProfileint(username);
        comment.setAfter_review(afer);
        comment.setProfileint(use.getName());
        comment.setAfter_name(afer_name);
        comment.setProfile_text(text);
        comment.setFile_img(use.getFile_img());
        comment.setClick(0);
        comment.setPublish_data(new Date(new java.util.Date().getTime()));
        if (ms.setComment(comment))
            return "发布成功";
        return "发布失败";
    }

}
