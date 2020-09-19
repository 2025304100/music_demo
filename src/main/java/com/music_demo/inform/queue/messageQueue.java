package com.music_demo.inform.queue;

import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class messageQueue {
    //创建一个无限的仓库
    private static LinkedBlockingQueue them = new LinkedBlockingQueue();

    //生产
    protected static void addsender(Map<String, String> map) {
        if (map == null) {
            System.out.println("空指针错误!");
            throw new NullPointerException();
        }
        if (map.size() == 0) {
            System.out.println("承载的函数为空");
            throw new NullPointerException();
        }
        try {
            them.put(map);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //定义消费者
    protected static Map<String, String> consumptionSend() {
        Map<String, String> map = null;
        try {
            map = (Map<String, String>) them.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return map;
    }
}
