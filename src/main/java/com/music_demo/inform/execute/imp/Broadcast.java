package com.music_demo.inform.execute.imp;

import java.util.Map;

public class Broadcast {
    private String push;
    //推送内容
    private Map<String, String> map;

    public boolean Push(String push, Map<String, String> map) {
        this.push = push;
        this.map = map;
        return false;
    }
}
