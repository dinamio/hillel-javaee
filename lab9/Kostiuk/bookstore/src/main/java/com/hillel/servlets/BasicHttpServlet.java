package com.hillel.servlets;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BasicHttpServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
