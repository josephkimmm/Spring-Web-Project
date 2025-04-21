package com.lighting.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.mypage.model.MeetingDAO;
import com.lighting.mypage.model.ParticipationDTO;

@WebServlet("/mypage/applystatus.do")
public class ApplyStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        int meetingPostSeq = Integer.parseInt(req.getParameter("meetingPostSeq"));
        
        MeetingDAO dao = new MeetingDAO();
        List<ParticipationDTO> applicants = dao.getApplicantsByPostSeq(meetingPostSeq);
        
        req.setAttribute("meetingPostSeq", meetingPostSeq); // 🔸 JSP에서 hidden input으로 쓸 값
        req.setAttribute("applicants", applicants);          // 🔸 신청자 리스트 전달

        req.getRequestDispatcher("/WEB-INF/views/mypage/applystatus.jsp").forward(req, resp);
    }
}
