package com.music_demo.administrator.function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@ConfigurationProperties(prefix = "music.chech.title")
public class content_Title {
    private String titles;

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getTitles() {
        return titles;
    }

    public List<String> getContent() {
        List<String> title = new ArrayList<>();
        int i = 0;
        while ((i = titles.indexOf(",")) != -1) {
            title.add(titles.substring(0, titles.indexOf(',')));
            titles = titles.substring(i + 1);
        }
        title.add(titles);
        return title;
    }
}
