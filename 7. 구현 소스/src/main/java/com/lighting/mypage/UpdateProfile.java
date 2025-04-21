package com.lighting.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage/updateprofile.do")
public class UpdateProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object userSeqObj = session.getAttribute("auth");

        if (userSeqObj == null) {
            resp.sendRedirect("/lighting/login.do");
            return;
        }

        int userSeq = (userSeqObj instanceof Integer)
            ? (Integer) userSeqObj
            : Integer.parseInt(userSeqObj.toString());

        // 필요하다면 사용자 정보 조회
        // MemberDAO dao = new MemberDAO();
        // MemberDTO member = dao.getMemberBySeq(userSeq);
        // req.setAttribute("member", member);

        req.getRequestDispatcher("/WEB-INF/views/mypage/updateprofile.jsp").forward(req, resp);
    }


}