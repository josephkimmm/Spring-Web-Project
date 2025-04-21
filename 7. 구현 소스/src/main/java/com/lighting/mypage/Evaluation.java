package com.lighting.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.mypage.model.EvaluationDAO;

@WebServlet("/mypage/evaluation.do")
public class Evaluation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        int userSeq;

        try {
            userSeq = Integer.parseInt(session.getAttribute("auth").toString());
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            out.print("{\"status\":\"unauthorized\"}");
            out.close();
            return;
        }

        String friendIdParam = req.getParameter("friendId");
        int friendId;

        try {
            friendId = Integer.parseInt(friendIdParam);
        } catch (NumberFormatException e) {
            PrintWriter out = resp.getWriter();
            out.print("{\"status\":\"invalid_parameter\"}");
            out.close();
            return;
        }

        EvaluationDAO dao = new EvaluationDAO();
        double avgScore = dao.getAverageScore(friendId);

        PrintWriter out = resp.getWriter();
        out.print("{\"score\":" + avgScore + "}");
        out.close();
    }
}
