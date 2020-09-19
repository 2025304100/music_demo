package com.music_demo.monitor.service;

import com.music_demo.entity.information.PageSer;

import com.music_demo.mapper.dao.Message;
import com.music_demo.monitor.service.imp.monitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class pageViewImp implements monitoring {
    @Autowired
    Message mes;

    public Integer setPageView(PageSer pageS, String tiltle) {

        return -1;
    }
}
