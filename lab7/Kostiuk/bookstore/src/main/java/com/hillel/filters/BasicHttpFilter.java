package com.hillel.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BasicHttpFilter extends Filter {

    @Override
    default void init(FilterConfig filterConfig) throws ServletException {}


    @Override
    default void destroy() {}

    void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    @Override
    default void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doHttpFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

}
