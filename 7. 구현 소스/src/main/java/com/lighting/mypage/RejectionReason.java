package com.lighting.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.mypage.model.RejectionDAO;
import com.lighting.mypage.model.RejectionInfoDTO;

@WebServlet("/mypage/rejectionreason.do")
public class RejectionReason extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
        int requestSeq = Integer.parseInt(req.getParameter("requestSeq"));
        RejectionDAO dao = new RejectionDAO();
        RejectionInfoDTO dto = dao.getRejectionInfo(requestSeq);

        if (dto != null) {
            req.setAttribute("nickname", dto.getNickname());
            req.setAttribute("title", dto.getTitle());
            req.setAttribute("reason", dto.getReason());
        } else {
            req.setAttribute("nickname", "정보 없음");
            req.setAttribute("title", "정보 없음");
            req.setAttribute("reason", "해당 거절 사유가 존재하지 않습니다.");
        }

        req.getRequestDispatcher("/WEB-INF/views/mypage/rejectionreason.jsp").forward(req, resp);
    }
}
