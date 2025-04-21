package com.lighting.meeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.meeting.model.MeetingDAO;
import com.lighting.meeting.model.MemberDTO;

@WebServlet("/meeting/addfriend.do")
public class AddFriend extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //AddFriend.java
        req.setCharacterEncoding("UTF-8");
        
        String requestedMemberSeq = req.getParameter("requestedMemberSeq");
        String requestingMemberSeq = req.getParameter("requestingMemberSeq");
        
        MeetingDAO dao = new MeetingDAO();
        MemberDTO dto = new MemberDTO();
        dto.setTblMemberSeq(requestedMemberSeq);
        
        dto = dao.getMemberInfo(dto);
        dao.close();
        
        String scoreImage = "";
        
        double score = Double.parseDouble(dto.getScore());
        
        if (score < 2) {
            scoreImage = "실버.png";
        } else if (score < 3) {
            scoreImage = "골드.png";
        } else if (score < 4) {
            scoreImage = "다이아.png";
        } else {
            scoreImage = "마스터.png";
        }
        
        req.setAttribute("requestingMemberSeq", requestingMemberSeq);
        req.setAttribute("scoreimage", scoreImage);
        req.setAttribute("dto", dto);
        req.getRequestDispatcher("/WEB-INF/views/meeting/addfriend.jsp").forward(req, resp);
    }

}
