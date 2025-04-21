package com.lighting.meeting;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.meeting.model.MeetingDAO;
import com.lighting.meeting.model.MeetingPostDTO;
import com.lighting.meeting.model.MemberDTO;

@WebServlet("/meeting/delete.do")
public class Delete extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
        String tblMeetingPostSeq = req.getParameter("tblMeetingPostSeq");
		
        MeetingDAO dao = new MeetingDAO();
        MeetingPostDTO meetingPostdto = dao.getPostInfo(tblMeetingPostSeq);
        MemberDTO memberdto = dao.getMemberInfo(tblMeetingPostSeq);
        List<MemberDTO> list = dao.getParticipantInfo(tblMeetingPostSeq);
        int checkMeeting = dao.getMeetingInfo(tblMeetingPostSeq);

        dao.close();
        
        req.setAttribute("checkMeeting", checkMeeting);
        req.setAttribute("tblMeetingPostSeq", tblMeetingPostSeq);
        req.setAttribute("list", list);
        req.setAttribute("memberdto", memberdto);
        req.setAttribute("meetingPostdto", meetingPostdto);
		req.getRequestDispatcher("/WEB-INF/views/meeting/delete.jsp").forward(req, resp);
	}

}
