package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.LoginServlet.currentSessions;

@WebFilter(value = "/view/registered/*")
public class UserFilter implements Filter {
    private boolean checkSession(String arg) {
        return arg != null && currentSessions.containsKey(arg);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(checkSession(((HttpServletRequest)servletRequest).getSession().getId())){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
