package com.music_demo.controller_model.cotroller;

import com.fasterxml.jackson.annotation.JsonView;
import com.music_demo.entity.music;
import com.music_demo.entity.musicType;
import com.music_demo.entity.music_des;
import com.music_demo.error_model.err.coding;
import com.music_demo.mapper.dao.Subassembly;
import com.music_demo.path.Host;
import com.music_demo.security_module.encryption_model.Base64;
import com.music_demo.service_model.music2.proxy.Proxy;
import com.music_demo.service_model.music2.service.Musicimp;
import com.music_demo.service_model.music2.service.SubassemblySer;
import com.music_demo.service_model.music2.service.offcer.musicMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping(value = "/music", produces = "application/json")
public class musicInterfaceDate {
    @Autowired
    Musicimp mus;
    @Autowired
    Host path;
    @Autowired
    Map<String, String> navigation;
    @Autowired
    SubassemblySer sub;
    @Value("${music.host.client.Host}")
    private String clientHost;
    @Value("${music.host.server.Host}")
    private String serverHost;
    private static Integer index;
    private final Integer TITLE = 20;
    private Logger log = Logger.getLogger(musicInterfaceDate.class);

    //防止数据镂空
    @GetMapping("/")
    public ModelAndView dataInerfaceTable(HttpSession ses) {
        ModelAndView mo = dataInerfaceTable(ses, null, 100000, 1);
        return mo;
    }

    //路口
    @GetMapping("/{type}/{page}/{date}")
    public ModelAndView dataInerfaceTable(HttpSession ses, @PathVariable("date") Integer date, @PathVariable("type") Integer type, @PathVariable("page") Integer page) {
        if (type == null || page == null) {
            throw new NullPointerException(musicInterfaceDate.class + "获取type,page为空");
        }
        index = Count() / TITLE + 1;
        List<Map<String, String>> but = sub.getButtonNavigtion(page, index, "/music/" + type + "/", "/" + date);
        //信息获取
        String name = (String) ses.getAttribute("username");
        Proxy music = new Proxy(mus);
        Map<String, Object> context = null;
        musicMessage musicimp = (musicMessage) java.lang.reflect.Proxy.newProxyInstance(musicMessage.class.getClassLoader(), new Class[]{musicMessage.class}, music);
        if (name == null)
            context = musicimp.getMusicTimtle(page, TITLE, type, "context", date);
        else if (name != null)
            context = musicimp.getUserMusicTimtle(page, TITLE, type, "context", date, name);

        List<music> mi = (List<music>) context.get("musicBean");
        List<music_des> mu = (List<music_des>) context.get("music_desBean");
        ModelAndView mv = new ModelAndView();
        mv.addObject("indexs", but);
        //加入键值
        mv.addObject("titles", mu);
        //加入备用信息
        ses.setAttribute("musicBean", mi);
        //获取显示
        ses.setAttribute("music_desBean", mu);
        //加入路径连接
        mv.setViewName("form/title.html");
        return mv;
    }
    //获取时间

    /**
     * @param date
     * @return navigation
     */
    @RequestMapping("title/date/{date}")
    public ModelAndView titleDate(HttpSession ses, @PathVariable("date") Integer date) {
        Integer type = (Integer) ses.getAttribute("navigation_type");
        if (type == null)
            type = 100000;
        ModelAndView modelAndView = dataInerfaceTable(ses, date, type, 1);
        return modelAndView;
    }

    @RequestMapping("title/type/{type}")
    public ModelAndView titleType(HttpSession ses, @PathVariable("type") Integer type) {
        ses.setAttribute("navigation_type", type);
        ModelAndView modelAndView = dataInerfaceTable(ses, 0, type, 1);
        return modelAndView;
    }

    //获取样式
    @RequestMapping("title")
    public ModelAndView titleType() {
        ModelAndView model = new ModelAndView();
        Proxy proxy = new Proxy(mus);
        musicMessage musicimp = (musicMessage) java.lang.reflect.Proxy.newProxyInstance(musicMessage.class.getClassLoader(), new Class[]{musicMessage.class}, proxy);
        //获取最新时间
        int year = mus.getNewDate();
        //时间表单
        List<Map<String, String>> dateList = mus.getNewDates();
        List<musicType> typeList = sub.getAllMusicType();
        model.addObject("dates", dateList);
        model.addObject("types", typeList);
        model.getModel().put("serverHost",serverHost);
        model.getModel().put("clientHost",clientHost);
        model.setViewName("music_liebie");

        return model;
    }


    //获取所有列数
    @RequestMapping("/count")
    public Integer Count() {
        return mus.getCount();
    }

    /**
     * 改进数据
     */
    @GetMapping("/imgfile/{id}")

    public @ResponseBody
    String img_View(HttpServletResponse rep, HttpServletResponse req, HttpSession ses, @PathVariable("id") String id) throws IOException, coding {
        List<music> mi = (List<music>) ses.getAttribute("musicBean");
        if (mi == null)
            throw new NullPointerException();
        String idm;
        String img_file = null;
        for (music mu : mi) {
            idm = mu.getIdm();
            if (idm.equals(id)) {
                img_file = mu.getFile_img();
            }
        }
        //获取链接
        File file = new File(img_file);
        if (file.isDirectory()) {
            throw new NullPointerException("获取的io流为空");
        }
        OutputStream out = req.getOutputStream();
        InputStream in = new FileInputStream(file);
        //获取byte流
        byte[] bit = FileCopyUtils.copyToByteArray(in);
        String sub = img_file.substring(img_file.lastIndexOf(".") + 1);
        rep.setHeader("Content-Type", "image/" + sub);
        out.write(bit);
        out.close();
        out.flush();
        return "ok";
    }

    /**
     * @author 20253  周国强
     * @version 1.1(音乐解析改进)
     */
    @GetMapping("/audio/{idpas}")
    public @ResponseBody
    String audioMusic(HttpServletResponse rep, HttpServletRequest req,
                      HttpSession ses, @PathVariable("idpas") String idpas) throws FileNotFoundException {
        List<music> mi = (List<music>) ses.getAttribute("musicBean");
        if (mi == null)
            throw new NullPointerException();
        String idm;

        String music_file = null;
        String suffix = null;
        for (music mu : mi) {
            idm = mu.getIdm();
//            String idmstr=new String(Base64.decode(idm.getBytes()),"utf-8");
//            //编码为十 十六  八进制
//            Long id=Long.decode(idm);
            if (idm.equals(idpas)) {
                music_file = mu.getFile_music();
                suffix = music_file.substring(music_file.lastIndexOf('.') + 1);//截取 前保后不保
            }
        }
        //io读取
        File io = new File(music_file);
        InputStream in = new BufferedInputStream(new FileInputStream(io));
        try {
            OutputStream out = rep.getOutputStream();
            //编译(复制文件 以二进制转换)
            byte[] bit = FileCopyUtils.copyToByteArray(in);
            //流传输
            out.write(bit);


            //浏览器配置
            //配置文件类型
            System.out.println(suffix);
            // rep.setContentType("audio/"+suffix);
            rep.setHeader("Content-Type", "audio/" + suffix);
            //文件大小(长度)
            rep.setHeader("Content-Length", bit.length + "");
            //设置起始间和结束时间--表示位置(以大小单位来计算) 起始进度  结尾进度
            rep.setHeader("Content-Range", "bytes 0-" + bit.length + "/" + bit.length);
            //设置文件传输方式(以byte 为为单位传输) 实现传输
            rep.setHeader("Accept-Ranges", "bytes");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
