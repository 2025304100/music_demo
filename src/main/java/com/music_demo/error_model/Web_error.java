package com.music_demo.error_model;


import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.ModelAndView;


//全局标签

public class Web_error {
    private Logger log = Logger.getLogger(com.music_demo.error_model.Web_error.class);

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView Exception1(Exception e) {
        ModelAndView view = new ModelAndView();
        log.info(e.getMessage());
        return view;
    }
}
