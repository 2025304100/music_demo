package com.music_demo.inform.execute;


import com.music_demo.inform.execute.imp.Broadcast;
import com.music_demo.inform.middle.Email;
import com.music_demo.inform.performera.agencyEmail;
import com.music_demo.inform.queue.Consumer;
import com.music_demo.inform.queue.Roducer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class emailPush extends Broadcast {

    //创建线程池
    private static ExecutorService fixed = Executors.newFixedThreadPool(5);
    private final Lock lock = new ReentrantLock();
    private static agencyEmail as = null;

    //内部类路口
    public emailPush() {
        if (as == null) {
            as = new agencyEmail();
            as.init();
        }
    }

    public myEmail aisle() {
        //上锁
        lock.lock();
        myEmail myEmail;
        try {
            myEmail = new myEmail();
        } finally {
            lock.unlock();
        }


        return myEmail;
    }

    static public class myEmail {

        private Email email;

        public myEmail() {
            email = new Email();
        }


        //生产接口
        public void Push(Map<String, String> map) {

            Roducer.InputRed(map);
        }

        //消费接口
        public Map<String, String> sendEmail() {
            Map<String, String> map = null;
            try {
                map = Consumer.GinRes();
            } catch (Exception e) {
                System.out.println("发生异常:" + 443);


            }
            return map;
        }
    }

    /**
     * 获取/处理数据异常的方法
     *
     * @param map
     */
    public void exceptionHandling(Map<String, String> map) {
        //重新进入生产接口
        try {
            if (map.get("error") == null) {
                map.put("error", "1");
                aisle().Push(map);
            } else {
                //通知模组
                System.out.println("发送失败" + map.get("username"));
            }

        } catch (Exception e) {
            System.out.println("异常");
        }


    }

}

class Timing extends Thread {

    @Override
    public void run() {

    }
}
