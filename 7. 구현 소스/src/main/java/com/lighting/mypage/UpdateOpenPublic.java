package com.lighting.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.mypage.model.MemberDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.mypage.model.MemberDAO;

@WebServlet("/mypage/updateopenpublic.do")
public class UpdateOpenPublic extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object userSeqObj = session.getAttribute("auth");

        if (userSeqObj == null) {
            resp.sendRedirect("/lighting/login.do");
            return;
        }

        int userSeq = (userSeqObj instanceof Integer)
            ? (Integer) userSeqObj
            : Integer.parseInt(userSeqObj.toString());

        String statusStr = req.getParameter("status");
        if (statusStr == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int status = Integer.parseInt(statusStr);

        MemberDAO dao = new MemberDAO();
        int result = dao.updatePublicStatus(userSeq, status);

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        if (result == 1) {
            writer.println("<script>alert('공개 여부가 성공적으로 변경되었습니다.'); window.close(); </script>");
        } else {
            writer.println("<script>alert('공개 여부 변경에 실패했습니다.'); history.back();</script>");
        }
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 여기서도 로그인 체크 해주는 게 좋음
        HttpSession session = req.getSession();
        Object authObj = session.getAttribute("auth");

        if (authObj == null) {
            resp.sendRedirect("/lighting/user/login.do");
            return;
        }

        int userSeq = (authObj instanceof Integer)
            ? (Integer) authObj
            : Integer.parseInt(authObj.toString());

        req.getRequestDispatcher("/WEB-INF/views/mypage/updateopenpublic.jsp").forward(req, resp);
    }
}
