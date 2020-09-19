package com.music_demo.service_model;

import com.music_demo.entity.information.PageSer;
import com.music_demo.entity.information.PageView;
import com.music_demo.function.dataFormat;
import com.music_demo.mapper.dao.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class visit {
    @Autowired
    private Message mes;
    private Logger log = Logger.getLogger(com.music_demo.service_model.visit.class);

    /**
     * pv--数据流
     * title--表单
     *
     * @param pv
     * @param title
     * @return
     */
    public int uploadingStream(PageView pv, String title) {
        if (pv == null || title == null) {
            log.error("数据获取 PageView title 数据获取为空");
            throw new NullPointerException("数据获取 PageView title 数据获取为空");
        }
        int is = mes.setPage(pv, title);
        return is;
    }

    public List<PageView> Gain(String title) {
        int time = dataFormat.IntegerFormat();
        List<PageView> pageView = mes.getlPage(title, time);
        if (pageView == null) {
            log.error("pageView获取为空");
            throw new NullPointerException();
        }
        return pageView;
    }

    //精确获取时间
    public PageView oneGain(String title, int time) {
        if (time == 0 || title == null) {
            log.error("数据获取 PageView title 数据获取为空");
            throw new NullPointerException("数据获取 PageView title 数据获取为空");
        }
        PageView pageView = mes.onePage(title, time);
        if (pageView == null) {
            log.error("pageView查询失败");
            throw new NullPointerException();
        }
        return pageView;
    }

    //月份季度统计
    public int monthGain(int newtime, int oldtime) {
        if (newtime == 0 || oldtime == 0) {
            log.error("数据获取 newtime,oldtime 数据获取为空");
            throw new NullPointerException("数据获取 newtimem,oldtime 数据获取为空");
        }
        PageView pageView = mes.setQuarter("pageviewday", newtime, oldtime);
        if (pageView == null) {
            log.error("pageView获取季度为空");
            throw new NullPointerException();
        }
        pageView.setIndex(oldtime / 100);
        //写入数据

        int s = uploadingStream(pageView, "pageviewmonth");
        return s;
    }

    public int yearGain(int newtime, int oldtime) {
        if (newtime == 0 || oldtime == 0) {
            log.error("com.music_demo.service_model 数据获取 newtime,oldtime 数据获取为空");
            throw new NullPointerException("com.music_demo.service_model 数据获取 newtimem,oldtime 数据获取为空");
        }
        //获取月份时间
        PageView pageView = mes.setQuarter("pageviewmonth", newtime / 100, oldtime / 100);
        if (pageView == null) {
            log.error("com.music_demo.service_model获取季度为空");
            throw new NullPointerException();
        }
        pageView.setIndex(oldtime / 10000);
        uploadingStream(pageView, "pageviewyear");
        return 0;
    }

}
