package com.hillel.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.model.Country;
import com.hillel.service.CountryService;
import com.hillel.service.impl.CountryServiceImpl;
import com.hillel.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rest/countries")
public class CountriesServlet extends BasicHttpServlet {

    @Autowired
    private CountryService countryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Utils.requestID(req).orElse(-1L);

        if (id == -1) {
            List<Country> countries = countryService.getAll();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(), countries);
            return;
        }
        resp.sendRedirect(req.getRequestURI());
    }

}
