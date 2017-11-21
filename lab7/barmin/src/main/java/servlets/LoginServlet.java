package servlets;



//import serviceDB.MySqlService;

import database.dao.UserDao;
import database.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    public static Map<String, String > currentSessions = new HashMap<>();//<jsessionid, username>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        int result = new UserDaoImpl().loginUser(username, password);
        switch (result){
            case 0:{
                currentSessions.put(req.getSession().getId(), username);
                req.getRequestDispatcher("/view/registered/welcome.jsp").forward(req, resp);
                break;
            }
            case -2:{
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "No such user");
                break;
            }
            case -1:{
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Wrong password");
                break;
            }
        }

    }
}
