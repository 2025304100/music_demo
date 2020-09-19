package com.music_demo.controller_model.cotroller;

import com.music_demo.entity.music_des;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("proxy/resource")
public class musicGain {
    //获取
    @RequestMapping("/oneMusic/{idpas}")
    public music_des getOneMusic(HttpSession ses, @PathVariable("idpas") String idpas) {
        List<music_des> list = (List<music_des>) ses.getAttribute("music_desBean");
        music_des mi = null;
        for (music_des ms : list) {
            String idpasm = ms.getIdpas();
            if (idpas.equals(idpasm)) {
                mi = ms;
            }
        }
        return mi;
    }
}
