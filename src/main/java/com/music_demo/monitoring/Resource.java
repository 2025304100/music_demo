package com.music_demo.monitoring;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.music_demo.Information_model.email.email;

import com.music_demo.path.isMonitoring;
import com.music_demo.service_model.system.AdminUser;
import com.sun.management.OperatingSystemMXBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import  java.lang.*;
public class Resource {
    @Autowired
    email em;
    @Autowired
    AdminUser admin;
    @Autowired
   isMonitoring is;
    private String[] resu;
    private static Double[] oldResu;
    private Long time;
    //第二个数据暂存点 加锁
    private Map<String, String> oldmap = new HashMap<>();
    //第一数据
    private Map<String, String> newmap = new HashMap<>();
    //日志加载去
    private Logger log = Logger.getLogger(com.music_demo.monitoring.Resource.class);
    private static OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public static int cpuLoad() throws InterruptedException {

        double cpuLoad = osmxb.getSystemCpuLoad();
        int percentCpuLoad = (int) (cpuLoad * 100);
        return percentCpuLoad;
    }

    public static int memoryLoad() {
        double totalvirtualMemory = osmxb.getTotalPhysicalMemorySize();
        double freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        double value = freePhysicalMemorySize / totalvirtualMemory;
        int percentMemoryLoad = (int) ((1 - value) * 100);
        return percentMemoryLoad;

    }

    //获取当时使用系统
    public static String isWindowsOrLinux() {

        String osName = System.getProperty("os.name");
        String sysName = "";
        if (osName.toLowerCase().startsWith("windows")) {
            sysName = "windows";
        } else if (osName.toLowerCase().startsWith("linux")) {
            sysName = "linux";
        }
        return sysName;
    }

    public static Map<String, String> getNetworkThrough() {
        Map<String, String> map;
        String th = isWindowsOrLinux();
        if (th.equals("windows")) {
            return getNetworkThroughputFor("netstat -e", "windows");
        } else {
            return getNetworkThroughputFor("ifconfig", "linux");
        }
    }

    public static Map<String, String> getNetworkThroughputFor(String exi, String th) {
        Process pro1 = null;
        int isbei = 8;//注意liunx是按字节来算的 windows是按位来算
        Map<String, String> map = new HashMap<>();
        Runtime r = Runtime.getRuntime();
        BufferedReader input = null;
        String rxPercent = "";
        String txPercent = "";
        try {
            String command = exi;

            pro1 = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
            String result1[] = readInLine(input, th);
            Double forwar_total = Double.valueOf(result1[0]);
            Double reception_tolal = Double.valueOf(result1[1]);

            //数组1--接受   数组2--发送
            if (oldResu == null) {
                //声明对象
                oldResu = new Double[2];
                map.put("forward", "0");
                map.put("reception", "0");
                oldResu[0] = forwar_total;
                oldResu[1] = reception_tolal;
                return map;
            }
            if (th.equals("linux")) {
                isbei = 1;
            }
            int rx = (int) ((forwar_total - oldResu[0]) * 100 / (isbei * 1024));
            int tx = (int) ((reception_tolal - oldResu[1]) * 100 / (isbei * 1024));
            rxPercent = String.valueOf((float) rx / 100);
            txPercent = String.valueOf((float) tx / 100);
            map.put("forward", rxPercent);
            map.put("reception", txPercent);

            oldResu[0] = forwar_total;
            oldResu[1] = reception_tolal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String[] readInLine(BufferedReader input, String osType) {
        String rxResult = "";
        String txResult = "";
        Boolean isrx = true, istx = true;
        StringTokenizer tokenStat = null;
        try {
            if (osType.equals("linux")) { // 获取linux环境下的网口上下行速率
                for (int i = 0; ; i++) {
                    String tx = input.readLine();
                    if (tx.indexOf("RX") != -1 && isrx) {
                        String[] txz = tx.split(" ");
                        for (int j = 0; ; j++) {
                            if (txz[j].indexOf("bytes") != -1) {
                                rxResult = txz[j + 1];
                                break;
                            }
                        }
                        isrx = false;
                    }
                    if (tx.indexOf("TX") != -1 && istx) {
                        String[] txz = tx.split(" ");
                        for (int j = 0; ; j++) {
                            if (txz[j].indexOf("bytes") != -1) {
                                txResult = txz[j + 1];
                                break;
                            }
                        }
                        istx = false;
                        break;
                    }
                }

            } else { // 获取windows环境下的网口上下行速率
                input.readLine();//根据cmd-可能为未知协议
                input.readLine();//错误
                input.readLine();//丢失
                input.readLine();//单广播数据
                //上面是获取一行-上面都是乱码 要StringTokenzer 字符串分段/
                tokenStat = new StringTokenizer(input.readLine());//直接
                tokenStat.nextToken();
                rxResult = tokenStat.nextToken();//读取下一行内容
                txResult = tokenStat.nextToken();
            }
        } catch (Exception e) {
        }
        String arr[] = {rxResult, txResult};
        return arr;
    }

    public synchronized void init() throws InterruptedException {
        Map<String, String> hemap = new HashMap<>();
        new Thread(() -> {
            while (true) {
                Map<String, String> map = getNetworkThrough();
                String cpuindex = null;
                try {
                    cpuindex = String.valueOf(cpuLoad());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String menevinxdex = String.valueOf(memoryLoad());
                String forward = map.get("forward");
                String reception = map.get("reception");

                //封装数据
                hemap.put("cpu", cpuindex);
                hemap.put("menory", menevinxdex);
                hemap.put("forward", forward);
                hemap.put("reception", reception);
                //日志计入
                //添加数据--替换更新
                Reabsit(hemap);
                //日记记录
                Joumal(hemap);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public Map<String, String> get_Huoqu() {
        return this.oldmap;
    }

    private void Reabsit(Map<String, String> map) {
        //数据替换后衮带机器
        synchronized (oldmap) {
            Map<String, String> newMap = this.newmap;
            this.newmap = map;
            this.oldmap = newMap;
        }
    }

    private void Joumal(Map<String, String> map) {
        //记录日志
        Integer cpi = Integer.valueOf(map.get("cpu"));
        Integer menray = Integer.valueOf(map.get("menory"));
        Float forawr = Float.valueOf(map.get("forward"));
        Float reception = Float.valueOf(map.get("reception"));
        if (is.getIs().equals("true"))
            System.out.println("上传:" + forawr + "KB/S    下载:" + reception + "KB/S");
        if (cpi > 85) {
            //cpu邮箱警告-设置信息发布推送等级
            log.info("cpu负载率:" + cpi);

        }
        if (menray > 90) {
            //内存饱满警告!
            log.info("内存负载率:" + menray);
        }
    }
}

