package com.music_demo.administrator.excute;

import com.music_demo.administrator.function.content_Title;
import com.music_demo.administrator.function.music_cache;
import com.music_demo.entity.music;
import org.apache.ibatis.annotations.Insert;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//长期代理

public class excute_Music {
    //服务更新周期-h
    private int data;
    //当前时间
    private int data_ds;
    @Autowired
    content_Title title;
    @Autowired
    com.music_demo.administrator.function.music_cache music_cache;
    private Logger log = Logger.getLogger(com.music_demo.administrator.excute.excute_Music.class);

    private static int Time = 86400000;
    private Demo dm = new Demo();

    //受代理的服务

    public String init() {
        if (dm.getState() == Thread.State.NEW) {
            Time = getTime() * 1000;
            System.out.println(Time);
            dm.start();

            return "启动成功!";
        } else if (dm.getState() == Thread.State.RUNNABLE) {
            return "任务已经在启动";
        } else if (dm.getState() == Thread.State.BLOCKED) {
            return "已经启动";
        }
        return "启动异常";
    }

    public static int getTime() {
        Date date = new Date();
        int time = date.getHours() * 3600 + date.getMinutes() * 60 + date.getSeconds();
        return 2 * 86400 - time;
    }

    public String ptocess() {
        if (dm.getState() == Thread.State.RUNNABLE) {
            dm.des();
            return "刷新成功!";
        }
        return "忙碌中";
    }

    public String destory() throws InterruptedException {
        if (dm.getState() == Thread.State.RUNNABLE) {
            dm.des();
            return "关闭成功";
        } else {
            Thread.sleep(10 * 1000);
            dm.des();
        }
        return "关闭延时";
    }

    class Demo extends Thread {
        public void admin() {
            while (true) {
                run();

            }
        }

        @Override
        public synchronized void run() {
            while (true) {
                try {
                    des();
                    wait(Time);
                    Time = 86400000;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public void des() {
            System.out.println("我启动了");
            List<String> ls = title.getContent();
            for (int i = 0; i < ls.size(); i++) {
                try {
                    music_cache.setMusic(ls.get(i));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                }

            }

        }
    }
}

