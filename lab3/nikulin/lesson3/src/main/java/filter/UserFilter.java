package filter;

import model.jdbc.JDBCUserDataAccessObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/books")
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println( ((HttpServletRequest)request).getPathInfo());
        System.out.println( ((HttpServletRequest)request).getHeaderNames());
        if (request.getParameter("signIn") != null){
            if (JDBCUserDataAccessObject.getJdbcUserDataAccessObject().getUser(request.getParameter("login"), request.getParameter("password")))
                chain.doFilter(request, response);
            else {
                response.getWriter().write("Nooooo");
                response.getWriter().flush();
                response.getWriter().close();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
