package servlets;



//import serviceDB.MySqlService;

import database.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value ="/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        if(new UserDaoImpl().registerUser(username, password)){
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Such user already exist");
        }
    }
}
