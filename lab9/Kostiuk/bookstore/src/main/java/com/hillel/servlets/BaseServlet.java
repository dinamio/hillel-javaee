package com.hillel.servlets;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebServlet({""})
public class BaseServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getAttribute("user");
        if (user != null) {
            req.getRequestDispatcher("/welcome").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
        }
    }
}
