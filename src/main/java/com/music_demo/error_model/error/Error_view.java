package com.music_demo.error_model.error;


import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Controller
public class Error_view extends BasicErrorController {


    /**
     * @param errorAttributes
     * @param serverProperties--重定向-便于更改路径
     * @param errorViewResolvers           原版-直接重用-不更改系统
     *                                     public Error_view(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
     *                                     super(errorAttributes, errorProperties, errorViewResolvers);
     *                                     }
     */
    public Error_view(ErrorAttributes errorAttributes, ServerProperties serverProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = getErrorAttributes(
                request, isIncludeStackTrace(request, MediaType.TEXT_HTML)
        );
        model.put("custommsg", "出错了" + status.toString());

        ModelAndView modelAndView = new ModelAndView("error", model, status);
        return modelAndView;
    }

    //不清楚--可有可不有
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

        Map<String, Object> body = getErrorAttributes(
                request, isIncludeStackTrace(request, MediaType.ALL)
        );
        body.put("custommsg", "出错了");
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }
}
