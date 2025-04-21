package com.lighting.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.user.model.UserDAO;

@WebServlet("/user/delmail.do")
public class DelMail extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        
        UserDAO dao = new UserDAO();
        dao.delEmail(email);
        dao.close();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        PrintWriter writer = resp.getWriter();
        writer.print("""
                {
                    "result": 1
                }
                """);
        writer.close();  
    }
}
