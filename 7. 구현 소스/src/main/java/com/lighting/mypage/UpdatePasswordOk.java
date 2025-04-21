package com.lighting.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.mypage.model.MemberDAO;

@WebServlet("/mypage/updatepasswordok.do")
public class UpdatePasswordOk extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Object userSeqObj = session.getAttribute("auth");

        if (userSeqObj == null) {
            resp.sendRedirect("/lighting/user/login.do");
            return;
        }

        int userSeq = (userSeqObj instanceof Integer)
            ? (Integer) userSeqObj
            : Integer.parseInt(userSeqObj.toString());

        String currentPw = req.getParameter("currentPassword");
        String newPw = req.getParameter("newPassword");

        MemberDAO dao = new MemberDAO();
        String savedPw = dao.getPassword(userSeq); // DB에서 현재 비번 불러오기

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        if (!savedPw.equals(currentPw)) {
            writer.println("<script>alert('기존 비밀번호가 일치하지 않습니다.'); history.back();</script>");
            writer.close();
            return;
        }

        dao.updatePassword(userSeq, newPw);

        writer.println("<script>alert('비밀번호가 성공적으로 변경되었습니다.'); window.close();</script>");
        writer.close();
    }


} 