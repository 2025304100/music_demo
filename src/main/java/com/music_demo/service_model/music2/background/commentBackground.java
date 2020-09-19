package com.music_demo.service_model.music2.background;

import com.music_demo.entity.Comment;
import com.music_demo.entity.User_entity;
import com.music_demo.mapper.dao.Resource;
import com.music_demo.mapper.dao.UserMapper;
import org.apache.log4j.Logger;


import java.util.concurrent.LinkedBlockingQueue;

public class commentBackground {
    //创建无界队列  安全队列
    final static LinkedBlockingQueue them = new LinkedBlockingQueue();

    /**
     * 创造生产者
     */
    private static Resource res;
    private static UserMapper userMapper;
    private static Logger log = Logger.getLogger(commentBackground.class);

    public static boolean Production(Comment com, Resource rest, UserMapper usermapper) {
        res = rest;
        userMapper = usermapper;

        boolean is = false;
        try {
            System.out.println("sdsad" + them.isEmpty());
            them.put(com);
            //队列为空时
            if (them.size() <= 1) {
                System.out.println("dfsdf");
                Consumer();
            }
            is = true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return is;
    }

    /**
     * 创建消费者
     */
    public static void Consumer() {
        //保持运行
        while (!them.isEmpty()) {
            Comment com = null;
            try {
                System.out.println("首运行");
                com = (Comment) them.take();
                System.out.println(com.getMusic_id());
                System.out.println("被运行");
                String after = com.getAfter_review();
                if (after != null) {
                    System.out.println(after);
                    User_entity user_entity = userMapper.login_User(after);
                    String name = user_entity.getName();
                    com.setAfter_name(name);
                }
                res.setComent(com);
                System.out.println("写入成功");
            } catch (InterruptedException e) {
                log.error("错误:" + e.getLocalizedMessage());
                putQueue(com);
                System.out.println(e.getLocalizedMessage());

            }

        }
    }

    private static void putQueue(Comment com) {
        try {
            them.put(com);
        } catch (InterruptedException e) {

            log.error(e.getLocalizedMessage());
        }
    }
}
