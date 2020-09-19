package com.music_demo.inform;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommunicantApplication {
    //发送人
    public void SendObjectOne(String objels);

    /**
     * key true瞬发
     * key false 排序发
     *
     * @param key
     * @param is
     */
    public void SendObjects(Map<String, String> key, boolean is);

    //阻塞时间
    public void oeriod(int selle);

    //定时发送
    public void transmission(Date date);

    //循环发送次数
    public void times(int time);

}
