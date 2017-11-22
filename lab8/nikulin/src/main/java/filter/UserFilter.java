package filter;

import model.hibernate.HibernateUserDataAccessObject;
import model.jdbc.JDBCUserDataAccessObject;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/books/login")
public class UserFilter implements Filter {

    private UserService userService = UserService.getUserService();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println( ((HttpServletRequest)request).getPathInfo());
        System.out.println( ((HttpServletRequest)request).getHeaderNames());
        if (request.getParameter("signIn") != null){
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            boolean isUserExist = userService.getUser(login, password);
            if (isUserExist)
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
