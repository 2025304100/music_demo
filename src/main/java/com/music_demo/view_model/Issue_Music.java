package com.music_demo.view_model;

import com.music_demo.entity.Comment;
import com.music_demo.entity.music;
import com.music_demo.entity.music_des;
import com.music_demo.error_model.err.coding;
import com.music_demo.error_model.err.error_Json;
import com.music_demo.redis_model.Music;
import com.music_demo.security_module.encryption_model.Base64;
import com.music_demo.service_model.comment_ser;
import com.music_demo.service_model.music2.service.Musicimp;
import com.music_demo.service_model.music_Resd;
import com.music_demo.service_model.select_music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Encoder;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/music")
public class Issue_Music {
    @Autowired
    Music music;
    @Autowired
    select_music sel;
    @Autowired
    comment_ser com;
    @Autowired
    Musicimp img;

    @RequestMapping("title/{type}/{key}")
    public @ResponseBody
    List<music_des> demo_Json(@PathVariable("type") String type, @PathVariable("key") String key) throws error_Json {
        //显示数
        int context = Integer.parseInt(key.substring(key.length() - 1));
        //页数
        int index = Integer.parseInt(key.substring(key.length() - 2, key.length() - 1));
        if (type.lastIndexOf("index") == -1) {
            throw new error_Json();
        }
        List<music_des> ls;
        List<music_des> ms = new ArrayList<>();
        ls = music.getMusic_list(type);

        int len = ls.size();

        for (int i = 20 * index; i < (index * 20 + context); i++) {
            ms.add(ls.get(i));
        }
        return ms;

    }

    @RequestMapping("sel/{type}/{id}")
    public @ResponseBody
    music_des music_Json(HttpSession ses, @PathVariable("type") String type, @PathVariable("id") String idpas) throws error_Json, UnsupportedEncodingException, coding {
        Map<String, com.music_demo.entity.music> ls;
        music is = null;
        if (!Base64.isBase64(idpas.getBytes()))
            throw new coding();
        String idsrc = new String(Base64.decode(idpas.getBytes()));
        long id = Long.parseLong(idsrc);
        ls = music.getMusic(type);
        if (ls == null) {
            //重缓存获取--单个获取
            is = music.getMusic_Temporary(idpas);
            if (is == null) {
                //从数据库获取
                String idsr = new String(Base64.decode(idpas.getBytes()));
                long i = Long.parseLong(idsrc);
                is = img.getMusic(i);//这里加个记录
            }
        }
        music_des ds = new music_des();
        ds.setIdpas(is.getIdm());
        ds.setMusic_index(is.getMusic_index());
        ds.setMusic_author_name(is.getMusic_author_name());
        ds.setFile_lyirc(is.getFile_lyirc());
        ds.setFile_img(is.getFile_img());
        ds.setMusic_name(is.getMusic_name());
        return ds;
    }

    @GetMapping("/imgfile/{type}/{id}")

    public @ResponseBody
    String img_View(HttpSession ses, HttpServletResponse response, @PathVariable("type") String type, @PathVariable("id") String id) throws IOException, coding {
        if (!Base64.isBase64(id.getBytes()))
            throw new coding();
        Map<String, music> map = music.getMusic(type);
        music mis = null;
        String img = null;
        if (map == null) {
            mis = music.getMusic_Temporary(id);
            if (mis == null) {
                mis = (com.music_demo.entity.music) ses.getAttribute(id);
                if (mis == null) {
                    System.out.println(id);
                    String idsr = new String(Base64.decode(id.getBytes()), "utf-8");

                    long i = Long.parseLong(idsr);
                    mis = this.img.getMusic(i);//这里加个记录
                }
                img = mis.getFile_img();
            }
        } else {
            img = map.get(id).getFile_img();
        }
        File scFileDir = new File(img);
        InputStream io = new FileInputStream(scFileDir);
        ServletOutputStream out = response.getOutputStream();
        byte[] is = FileCopyUtils.copyToByteArray(scFileDir);
        out.write(is);
        String p = img.substring(img.lastIndexOf(".") + 1);
        response.setHeader("Content-Type", "image/" + p);
//        String top = "data:image/" + img.substring(img.lastIndexOf(".") + 1) + ";base64,";
//        return top + Base64.encodeToString(is);
        out.flush();
        out.close();
        return "ok";
    }

    @GetMapping("/audio/{type}/{id}")
    public void music_Audio(HttpServletRequest request, HttpSession ses, HttpServletResponse response, @PathVariable("type") String type, @PathVariable("id") String id) throws IOException, coding {
        Map<String, music> map = music.getMusic(type);
        music mis = null;
        String audio = null;
        String prus = null;
        if (map == null) {
            //缓存获取
            mis = music.getMusic_Temporary(id);
            if (mis == null) {
                String idsr = new String(Base64.decode(id.getBytes()));
                long i = Long.parseLong(idsr);

                mis = this.img.getMusic(i);//这里加个记录
            }
            audio = mis.getFile_music();
        } else {
            audio = map.get(id).getFile_music();
        }
        //音乐类型
        prus = audio.substring(audio.lastIndexOf(".") + 1);
        //目前audio仅支持MP3-ogg wav
        if (prus != "mp3" || prus != "ogg" || prus != "wav") {
            //进行转码 -默认MP3
        }
        File file = new File(audio);
        //设置文件类型
        response.setContentType("audio/" + prus);
        response.setHeader("Content-Type", "audio/" + prus);
        ServletOutputStream op = response.getOutputStream();
        byte[] i = FileCopyUtils.copyToByteArray(file);
        //告诉浏览器大小
        response.setHeader("Content-Length", String.valueOf(i.length));
        //设置起始间和结束时间--表示位置(以大小单位来计算)
        response.setHeader("Content-Range", "bytes 0-" + i.length + "/" + i.length);
        //表示一byte(以byte位一个块)进行传输-便于可以拖动挑
        response.setHeader("Accept-Ranges", "bytes");

        op.write(i);
        op.close();
    }

    //必须经过中专数据获取id
    @GetMapping("/discuss/{context}")
    public @ResponseBody
    Map<String, Object> Discuss(@PathVariable("context") int contxt, HttpServletResponse response, HttpSession ses) throws IOException {
        music_des ds = (music_des) ses.getAttribute("music_des");
        List<Comment> lis = null;
        if (ds != null) {
            String idsrc = new String(Base64.decode(ds.getIdpas().getBytes()));
            long id = Long.parseLong(idsrc);
            lis = com.getComment(id);
        } else {
            lis = new ArrayList<>();
            //防止界面异常
            lis.add(new Comment());
        }
        Map<String, Object> map = new HashMap<>();
        //获取现有耶稣

        map.put("context", lis.size());
        map.put("comment", lis);
        return map;
    }

    //必须经过中专数据获取id
    @GetMapping("/music_index")
    public @ResponseBody
    music_des music_index(HttpSession ses) throws IOException {
        music_des d = (music_des) ses.getAttribute("music_des");
        System.out.println("获取后:" + ses.getId());
        System.out.println("dsffdd" + d.getIdpas());
        if (d == null) {
            //防止空指针产生
            d = new music_des();
        }

        return d;
    }

    //
    @PostMapping("comment/{username}")
    public @ResponseBody
    List<Comment> comment(@PathVariable("username") String name) {

        return null;
    }
}

