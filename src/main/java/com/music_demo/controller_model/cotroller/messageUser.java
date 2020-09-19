package com.music_demo.controller_model.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class messageUser {
    @GetMapping("message")
    public ModelAndView GetMessage() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
