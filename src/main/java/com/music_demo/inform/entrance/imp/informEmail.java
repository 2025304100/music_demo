package com.music_demo.inform.entrance.imp;

import com.music_demo.inform.entrance.inform;
import com.music_demo.inform.execute.emailPush;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class informEmail implements inform {

    @Override
    public Boolean pusNotifcation(Map<String, String> map) {
        new emailPush().aisle().Push(map);
        return true;
    }
}
