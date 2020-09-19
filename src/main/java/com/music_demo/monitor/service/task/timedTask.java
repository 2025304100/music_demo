package com.music_demo.monitor.service.task;

import com.music_demo.entity.information.PageType;
import com.music_demo.entity.information.PageView;
import com.music_demo.function.dataFormat;
import com.music_demo.monitor.recorder.userLogin;
import com.music_demo.service_model.visit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class timedTask {
    private static int month;
    private static int year;
    private int time;
    //总访问
    private Long Context = 0L;
    //直接访问
    private Long direct = 0L;
    //普通用户
    private Long consumer = 0L;
    //vip用户
    private Long noble = 0L;
    //钻石vip
    private Long respect = 0L;
    //基础对像

    private PageView pv;
    //细节对象
    private PageType pg;

    private Logger log = Logger.getLogger(com.music_demo.monitor.service.task.timedTask.class);
    @Autowired
    visit vi;

    public void init() {
        //启动预时间
        int time = 86400 - dataFormat.getResidue();
        //初始化时间
        this.month = dataFormat.getMonth();
        this.year = dataFormat.getYeay();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            int month = dataFormat.getMonth();
            this.time = dataFormat.IntegerFormat();
            day();
            if (month != this.month) {
                int year = dataFormat.getYeay();
                this.month = month;
                month();

                if (year != this.year) {
                    this.year = year;
                    year();
                }
            }

        }, time, 86400, TimeUnit.SECONDS);


    }

    public void day() {
        //
        Context = userLogin.Context;
        direct = userLogin.direct;
        consumer = userLogin.consumer;
        noble = userLogin.noble;
        respect = userLogin.respect;
        //对象注入
        pg = new PageType();
        pg.setVisitor_volume(Context);
        pg.setDirect(direct);
        pg.setConsumer(consumer);
        pg.setMemberPV(noble);
        pg.setSupermenberpv(respect);
        pv = new PageView();
        pv.setIndex(this.time);
        pv.setVisitor_volume(Context);
        pv.setPageType(pg);
        int s = vi.uploadingStream(pv, "pageviewday");
        if (s != 1) {
            log.error("日记些入失败");
        }
    }

    public void month() {

        int s = vi.monthGain(this.time, this.time - 100);
    }

    public void year() {
        int s = vi.yearGain(this.time, this.time - 10000);
    }
}
