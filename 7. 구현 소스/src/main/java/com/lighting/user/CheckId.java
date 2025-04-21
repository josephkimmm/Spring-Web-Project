package com.lighting.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.user.model.UserDAO;

@WebServlet("/user/register/checkId.do")
public class CheckId extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id"); // 클라이언트에서 전달된 id 파라미터

        UserDAO dao = new UserDAO();
        boolean isDuplicate = dao.isDuplicateId(id); // 중복 여부 체크

        // 응답을 JSON 형식으로 반환
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("{\"isDuplicate\": " + isDuplicate + "}");  // 중복 여부를 JSON 형식으로 응답
        out.flush();
    }
}