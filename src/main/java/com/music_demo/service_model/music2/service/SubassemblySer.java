package com.music_demo.service_model.music2.service;

import com.music_demo.entity.musicType;
import com.music_demo.mapper.dao.Subassembly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubassemblySer {
    @Autowired
    Subassembly sub;

    //获取音乐类型
    public List<musicType> getAllMusicType() {
        List<musicType> musicTypes = sub.getmusicType();

        //配置路径
        for (musicType is : musicTypes) {
            Integer type = is.getMusic_type();
            //路径配置(默认配置  1--页数  0---默认时间)
            is.setPage("/music/title/type/" + type);
        }
        return musicTypes;

    }

    //button下边导航拦
    private Integer len = 5;

    public List<Map<String, String>> getButtonNavigtion(Integer page, Integer max, String prefix, String suffix) {
        List<Map<String, String>> indexList = new ArrayList<>();
        Integer min = 1;
        if (page > 2) {
            min = page - 2;
        }

        if (5 < max) {
            indexList = getButtonNavigtionInsufficient(min, page, max, prefix, suffix);
        } else
            indexList = getButtonNavigtionSatisfy(min, page, max, prefix, suffix);
        return indexList;
    }

    //当大于5导航方式
    public List<Map<String, String>> getButtonNavigtionSatisfy(Integer min, Integer page, Integer max, String prefix, String suffix) {
        List<Map<String, String>> list = new ArrayList<>();
        if (max - min >= 4) {
            for (int i = min; i <= min + 5; i++) {
                Map<String, String> map = new HashMap<>();
                if (i == page) {
                    map.put("path", "#");
                    map.put("text", i + "");
                    map.put("style", "background-color:#07ffb59f");
                    list.add(map);
                    continue;
                }
                map.put("path", prefix + i + suffix);
                map.put("text", i + "");
                map.put("style", "background-color:#FFFFFF");
                list.add(map);
            }
        } else {
            list = getButtonNavigtionInsufficient(min, page, max, prefix, suffix);
        }
        return list;
    }

    //当小于5时导航方式
    public List<Map<String, String>> getButtonNavigtionInsufficient(Integer min, Integer page, Integer max, String prefix, String suffix) {
        List<Map<String, String>> list = new ArrayList<>();
        System.out.println(min);
        for (int i = min; i <= max; i++) {
            Map<String, String> map = new HashMap<>();
            if (i == page) {
                map.put("path", "#");
                map.put("text", i + "");
                map.put("style", "background-color:#07ffb59f");
                list.add(map);
                continue;
            }
            map.put("path", prefix + i + suffix);
            map.put("text", i + "");
            map.put("style", "background-color:#FFFFFF");
            list.add(map);
        }
        return list;
    }
}
