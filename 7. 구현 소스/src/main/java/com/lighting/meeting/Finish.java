package com.lighting.meeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.meeting.model.MeetingDAO;

@WebServlet("/meeting/finish.do")
public class Finish extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Finish.java
        req.setCharacterEncoding("UTF-8");
        String tblMeetingPostSeq = req.getParameter("tblMeetingPostSeq");
        
        MeetingDAO dao = new MeetingDAO();
        
        int result = dao.finish(tblMeetingPostSeq);
        dao.close();
    }

}
