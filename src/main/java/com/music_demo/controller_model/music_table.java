package com.music_demo.controller_model;

import com.music_demo.controller_model.resource.Resource_path;
import com.music_demo.entity.music;
import com.music_demo.redis_model.Music_Chache;
import com.music_demo.redis_model.Music_Up;
import com.music_demo.service_model.ResourceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("resource")
public class music_table {
    @Autowired
    Music_Chache music_chache;
    @Autowired
    Resource_path resource_path;
    @Autowired
    ResourceImp resourceImp;

    @RequestMapping("updata_music")
    public String Upada_Music(MultipartFile music_file, HttpSession sess) throws InterruptedException {
        //getContextPath()表示获取项目目录  get getRealPath("music_file_cache")-文件
        String path = resource_path.getMusicchach();
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        try {
            music_file.transferTo(new File(path, music_file.getOriginalFilename()));
            Music_Up music_up = new Music_Up();
            music_up.setMusicchach(music_file.getOriginalFilename());
            music_chache.SetDemo((String) sess.getAttribute("username"), music_up);

            return "发送成功";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "发送失败";
    }

    @RequestMapping("updata")
    public String Up_dada(MultipartFile file, String firstname, String author_name
            , String music_date, String music_zu, String music_denji, String music_geci, HttpSession sess) throws IOException {
        music mis = new music();
        if (file.getSize() == 0 || firstname == null || author_name == null || music_date == null || music_zu == null || music_denji == null)
            return "数据上传丢失!";
        String path = resource_path.getImg();
        System.out.println(firstname);
        String music_path = resource_path.getMusic();
        String musiccache_path = resource_path.getMusicchach();
        String img_old = file.getOriginalFilename();
        //注意截取是前留后不留
        String suffix = img_old.substring(img_old.lastIndexOf('.'));
        System.out.println(suffix);
        if (!suffix.equals(".png") && !suffix.equals(".jpg") && !suffix.equals(".JPG") && !suffix.equals(".PNG"))
            return "图片上传格式错误";
        String music_old = (music_chache.getDemo((String) sess.getAttribute("username"))).getMusicchach();
        System.out.println(music_old);
        String img_new = UUID.randomUUID().toString() + img_old.substring(img_old.lastIndexOf('.'));
        String music_new = firstname + "#" + UUID.randomUUID().toString() + music_old.substring(music_old.indexOf('.'), music_old.length());
        mis.setFile_img(path + img_new);
        mis.setFile_music_author_name((String) sess.getAttribute("username"));
        mis.setMusic_author_name(author_name);
        mis.setFile_music(music_path + music_new);
        mis.setMusic_name(firstname);
        mis.setMusic_cpy_music_data(music_date);
        mis.setMusic_type(Integer.valueOf(music_zu));
        mis.setMusic_index(Integer.valueOf(music_denji));
        //new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())
        mis.setMusic_data(new Date(new java.util.Date().getTime()));
        if (music_geci != null) {
            mis.setMusic_ts(1);
            mis.setFile_lyirc(music_geci);
        } else
            mis.setMusic_ts(0);
        File f = new File(music_path);
        if (!f.isDirectory()) {
            f.mkdirs();
        }

        InputStream in = new BufferedInputStream(new FileInputStream(musiccache_path + music_old));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(music_path + music_new));
        File file1 = new File(path);
        //但系统没有父目录,自动创建
        if (!file1.isDirectory()) {
            file1.mkdirs();
        }
        file.transferTo(new File(path, img_new));
        int b;
        try {
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            resourceImp.Music_Insert(mis);
            return "上传成功!";
        } catch (IOException e) {

        } finally {
            out.flush();
            out.close();
            in.close();
        }

        return "上传失败";
    }

}
