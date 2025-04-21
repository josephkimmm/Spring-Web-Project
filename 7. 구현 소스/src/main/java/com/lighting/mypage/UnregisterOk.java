package com.lighting.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.mypage.model.MemberDAO;

@WebServlet("/mypage/unregisterok.do")
public class UnregisterOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object userSeqObj = session.getAttribute("auth");

        if (userSeqObj == null) {
            resp.sendRedirect("/lighting/user/login.do");
            return;
        }

        int userSeq = (userSeqObj instanceof Integer)
            ? (Integer) userSeqObj
            : Integer.parseInt(userSeqObj.toString());

        MemberDAO dao = new MemberDAO();
        dao.updateStatus(userSeq, 1);

        // 세션 무효화
        session.invalidate();

        // 부모창 이동 + 현재 창 닫기
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write("<script>");
        resp.getWriter().write("alert('회원 탈퇴가 완료되었습니다.');");
        resp.getWriter().write("window.opener.location.href='/lighting/main.do';");
        resp.getWriter().write("window.close();");
        resp.getWriter().write("</script>");
    }
}