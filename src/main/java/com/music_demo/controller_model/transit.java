package com.music_demo.controller_model;

import com.music_demo.entity.Comment;
import com.music_demo.entity.music;
import com.music_demo.entity.music_des;
import com.music_demo.error_model.err.coding;
import com.music_demo.redis_model.Music;
import com.music_demo.security_module.encryption_model.Base64;
import com.music_demo.service_model.comment_ser;
import com.music_demo.service_model.music2.service.Musicimp;
import com.music_demo.service_model.select_music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class transit {
    @Autowired
    Music music;
    @Autowired
    select_music sel;
    @Autowired
    comment_ser cm;
    @Autowired
    Musicimp img;
    @Value("${music.host.server.Host}")
    String hors;
    @GetMapping("music_index/{type}/{id}")
    public @ResponseBody
    String music_Index(HttpSession ses, @PathVariable("type") String type, @PathVariable("id") String idpas) throws coding, UnsupportedEncodingException {
        Map<String, com.music_demo.entity.music> ls;
        if (!Base64.isBase64(idpas.getBytes()))
            throw new coding();
        String idsrc = new String(Base64.decode(idpas.getBytes("utf-8")));
        long id = Long.parseLong(idsrc);
        ls = music.getMusic(type);
        music is = null;
        if (ls == null) {
            //重缓存获取--单个获取
            is = music.getMusic_Temporary(idpas);
            if (is == null) {
                //从数据库获取
                String idsr = new String(Base64.decode(idpas.getBytes("utf-8")));
                long i = Long.parseLong(idsrc);
                is = img.getMusic(i);//这里加个记录
            }
        }
        //源数据---这个是结构性bug
        //由于单列模型,最终还是引用到一块的,垂直修改以最后位根据点--结构性bug
        String s = Base64.encodeToString(String.valueOf(is.getId()).getBytes());
        music_des ddm = new music_des();
        ddm.setIdpas(s);
        ddm.setContext(is.getContext());
        ddm.setFile_music(hors + "/music/music_audio/" + type + "/" + s);
        ddm.setFile_img(hors + "/music/imgfile/" + type + "/" + s);
        ddm.setFile_lyirc(is.getFile_lyirc());
        ddm.setMusic_index(is.getMusic_index());
        ddm.setMusic_name(is.getMusic_name());
        ddm.setMusic_author_name(is.getMusic_author_name());
        ses.setAttribute("music_des", ddm);//是存入地址
        List<Comment> comment = cm.getComment(id);
        System.out.println("获取前:" + ses.getId());
        ses.setAttribute(idpas, is);
        ses.setAttribute("comment", comment);
        return "ok";


    }
}
