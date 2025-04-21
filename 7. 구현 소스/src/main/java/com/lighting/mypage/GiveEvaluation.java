package com.lighting.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.mypage.model.MeetingDAO;
import com.lighting.mypage.model.MemberDTO;

@WebServlet("/mypage/giveevaluation.do")
public class GiveEvaluation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 세션에서 로그인 사용자 번호 가져오기
        HttpSession session = req.getSession();
        Object hostSeqObj = session.getAttribute("auth");

        if (hostSeqObj == null) {
            resp.sendRedirect("/lighting/login.do");
            return;
        }

        int hostSeq = (hostSeqObj instanceof Integer)
            ? (Integer) hostSeqObj
            : Integer.parseInt(hostSeqObj.toString());

        MeetingDAO dao = new MeetingDAO();
        int meetingSeq = dao.getLatestMeetingSeqByHostWithParticipants(hostSeq);
        int meetingPostSeq = dao.getMeetingPostSeqByMeetingSeq(meetingSeq); 
        List<MemberDTO> attendees = dao.getApprovedParticipantsNotYetEvaluatedByPostSeq(meetingPostSeq, hostSeq);
        
        req.setAttribute("meetingPostSeq", meetingPostSeq);
        req.setAttribute("meetingSeq", meetingSeq);
        req.setAttribute("attendees", attendees);
        req.setAttribute("evaluationCount", attendees.size());

        System.out.println("모임 시퀀스: " + meetingSeq);
        System.out.println("모임 게시글 시퀀스: " + meetingPostSeq);
        System.out.println("참석자 수: " + attendees);
        System.out.println("평가자 수: " + attendees.size());
        
        req.getRequestDispatcher("/WEB-INF/views/mypage/giveevaluation.jsp").forward(req, resp);
    }
}
