package com.music_demo.view_model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class Verify {
    @RequestMapping(value = "Io_img")
    public void Img(HttpServletResponse rep, HttpSession session) throws IOException {
        int width = 200;
        int height = 70;
        String verification_code = "";//verification_code不能声明为null,否则会以字符串表示

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //创建透明的玻璃
        //向画板声明一条画笔
        Graphics2D gra = image.createGraphics();
        //一个白色画笔
        gra.setColor(new Color(255, 255, 255));
        //画笔填充
        gra.fillRect(0, 0, width, height);
        Random ra = new Random();
        List<Object> list = new ArrayList<>();
        Color[] colors = new Color[]{Color.BLUE, Color.CYAN, Color.green, Color.MAGENTA, Color.red};
        char[] ch = new char[]{
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                'a', 'b', 'c', 'd', 'e', 'f'
                , 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'
                , 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'
                , 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        for (int m = 0; m < 4; m++) {
            list.add(ch[ra.nextInt(62)]);
        }
        //设置字体
        gra.setFont(new Font("", Font.ITALIC | Font.BOLD, 30));

        for (int f = 0; f < 3; f++) {
            gra.setColor(colors[ra.nextInt(5)]);
            gra.drawLine(ra.nextInt((width + 1) / 3), ra.nextInt((height + 1) / 2),
                    width - (ra.nextInt(width / 3)),
                    height - (ra.nextInt(height / 3))
            );
        }
        for (int i = 0; i < 4; i++) {
            gra.setColor(colors[ra.nextInt(5)]);
            //添加字体
            gra.drawString(list.get(i).toString(), i * 50, 55 + (ra.nextInt(21) - 20));
            verification_code += list.get(i).toString();
        }
        ServletOutputStream o = rep.getOutputStream();

        //将生成的图片以流的方式输出
        ImageIO.write(image, "jpg", o);

        session.setAttribute("verification_code", verification_code);

    }
}
