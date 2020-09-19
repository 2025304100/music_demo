package com.music_demo.controller_model;

import com.music_demo.security_module.encryption_model.single_encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.NoSuchAlgorithmException;

@Controller
public class mudic_sudio {

    @PostMapping("audio/{name}/{type}/{dm}")
    public String audioFile(@PathVariable("dm") String dm, HttpServletResponse response, @PathVariable("name") String name, @PathVariable("type") String type) throws NoSuchAlgorithmException {

        String ty = type.substring(type.lastIndexOf('#') + 1);
        String na = name.substring(name.lastIndexOf(name.indexOf(4)));
        String key = single_encryption.md5_key(na, "MD5");
        String id = type.substring(0, type.indexOf("#"));
        if (dm.equals("1")) {
            //缓存获取连接

        } else if (ty.equals("0")) {

            //数据库获取
        }

        //设置文件路径
        File file = new File("/Users/dalaoyang/Documents/dalaoyang.jpeg");
        //File file = new File(realPath , fileName);
        if (file.exists()) {
            response.setContentType("audio/mpeg");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + "de");// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return "下载失败";
    }
}
