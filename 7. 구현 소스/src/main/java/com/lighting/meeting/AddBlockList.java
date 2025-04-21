package com.lighting.meeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.meeting.model.BlockListDTO;
import com.lighting.meeting.model.MeetingDAO;

@WebServlet("/meeting/addblocklist.do")
public class AddBlockList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //AddBlockList.java
        req.setCharacterEncoding("UTF-8");
        String blockerMemberSeq = req.getParameter("blockerMemberSeq");
        String blockedMemberSeq = req.getParameter("blockedMemberSeq");
        
        BlockListDTO dto = new BlockListDTO();
        MeetingDAO dao = new MeetingDAO();
        
        dto.setBlockerMemberSeq(blockerMemberSeq);
        dto.setBlockedMemberSeq(blockedMemberSeq);
        
        int result = dao.addBlockList(dto);
        dao.close();
        
    }

}
