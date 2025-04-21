package com.lighting.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.mypage.model.BlockDAO;

@WebServlet("/mypage/unblock.do")
public class UnblockFriend extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        HttpSession session = req.getSession();
        Object auth = session.getAttribute("auth");

        if (auth == null) {
            resp.getWriter().write("{\"status\":\"unauthorized\"}");
            return;
        }

        int userSeq;
        try {
            userSeq = Integer.parseInt(auth.toString());
        } catch (NumberFormatException e) {
            resp.getWriter().write("{\"status\":\"unauthorized\"}");
            return;
        }

        String blockedIdParam = req.getParameter("blockedId");
        int blockedId;

        try {
            blockedId = Integer.parseInt(blockedIdParam);
        } catch (NumberFormatException e) {
            resp.getWriter().write("{\"status\":\"invalid_parameter\"}");
            return;
        }

        BlockDAO dao = new BlockDAO();
        boolean result = dao.unblockUser(userSeq, blockedId);

        resp.getWriter().write("{\"status\":\"" + (result ? "success" : "fail") + "\"}");
    }
}
