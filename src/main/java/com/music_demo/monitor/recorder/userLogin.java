package com.music_demo.monitor.recorder;

/**
 * 改模款用于记录登录人数
 */
public class userLogin {
    public static Long Context = 0L;

    //直接登录
    public static Long direct = 0L;
    //普通用户
    public static Long consumer = 0L;
    //vip用户
    public static Long noble = 0L;
    //钻石vip
    public static Long respect = 0L;

    //通用接口  添加模块
    private static void add(int index) {
        switch (index) {
            case -1:
                direct++;
                break;
            case 0:
                consumer++;
                break;
            case 1:
                noble++;
                break;
            case 2:
                respect++;

        }
    }

    private static void remove(int index) {
        switch (index) {
            case -1:
                direct--;
                break;
            case 0:
                consumer--;
                break;
            case 1:
                noble--;
                break;
            case 2:
                respect--;

        }
    }

    /**
     * 记录接口
     */
    public synchronized static void addContext(int index) {
        //默认添加
        Context++;
        add(index);

    }

    public synchronized static void amendContext(int oldindex, int newindex) {

        remove(oldindex);
        add(newindex);
    }

}
