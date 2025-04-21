package com.lighting.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage/setSessionStatus.do")
public class SetSessionStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 클라이언트로부터 전송된 status 값을 받음
        String status = req.getParameter("status");

        // 세션 객체 가져오기
        HttpSession session = req.getSession();

        // 세션에 status 속성 저장
        if (status != null) {
            session.setAttribute("status", status);
        }

    }
}
