package com.music_demo.inform.queue;

import java.util.Map;

public class Consumer {
    public static Map<String, String> GinRes() {
        Map<String, String> map = messageQueue.consumptionSend();
        if (map == null) {
            throw new NullPointerException();
        }
        return map;
    }
}
