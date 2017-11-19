package com.hillel.filters;

//import org.apache.commons.lang3.StringUtils;

import com.hillel.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthFilter implements Filter {

    private List<String> pathFilters = Arrays.asList("changingListBook.jsp", "addingBook.jsp");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        String path = substringAfterLast(uri,"/");

        if (!pathFilters.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("PRINCIPAL");

        if (user != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        ((HttpServletResponse) servletResponse).sendRedirect("registration.html");

    }

    @Override
    public void destroy() {

    }

    private static String substringAfterLast(final String str, final String separator) {
        if (str.isEmpty()) {
            return str;
        }
        if (separator.isEmpty()) {
            return "";
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == str.length() - separator.length()) {
            return "";
        }
        return str.substring(pos + separator.length());
    }
}
