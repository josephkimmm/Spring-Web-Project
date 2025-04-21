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

@WebServlet("/meeting/deleteok.do")
public class DeleteOk extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //DeleteOk.java
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        String tblMeetingPostSeq = req.getParameter("tblMeetingPostSeq");
        MeetingDAO dao = new MeetingDAO();
        MemberDTO dto = dao.getMemberInfo(tblMeetingPostSeq);
        
        session.getAttribute("auth");
        if (!dto.getTblMemberSeq().equals(session.getAttribute("auth"))) {
            System.out.println("로그인 아이디 != 작성자 아이디");
            return;
        }
        
        int result = dao.delete(tblMeetingPostSeq);
        dao.close();

    }

}
