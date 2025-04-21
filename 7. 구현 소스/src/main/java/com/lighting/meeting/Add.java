package com.lighting.meeting;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.meeting.model.MeetingDAO;
import com.lighting.meeting.model.MeetingPostDTO;

@WebServlet("/meeting/add.do")
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        String tblMemberSeq = (String) session.getAttribute("auth");
        
        if(tblMemberSeq != null) {
            
            MeetingDAO dao = new MeetingDAO();
            List<MeetingPostDTO> list = dao.getMeetingInfoList(tblMemberSeq);
            
            req.setAttribute("list", list);
            req.getRequestDispatcher("/WEB-INF/views/meeting/add.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
        }
        
    }

}









