package com.hillel.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class EncodingInterceptor extends HandlerInterceptorAdapter {

    private final static String UTF_8 = "UTF-8";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String encoding = request.getCharacterEncoding();
        if (encoding == null || !encoding.equals(UTF_8)) {
            request.setCharacterEncoding(UTF_8);
        }
        return true;
    }

}
