package com.lighting.meeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.lighting.meeting.model.BlockListDTO;
import com.lighting.meeting.model.MeetingDAO;

@WebServlet("/meeting/getblocklist.do")
public class GetBlockList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //GetBlockList.java
        
        req.setCharacterEncoding("UTF-8");
        String blockerMemberSeq = req.getParameter("blockerMemberSeq");
        String blockedMemberSeq = req.getParameter("blockedMemberSeq");
        
        BlockListDTO dto = new BlockListDTO();
        MeetingDAO dao = new MeetingDAO();
        
        dto.setBlockerMemberSeq(blockerMemberSeq);
        dto.setBlockedMemberSeq(blockedMemberSeq);
        JSONObject obj = new JSONObject();
        
        int result = dao.getBlockList(dto);
        dao.close();
        
        obj.put("result", result);
        
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(obj);
        writer.close();
        
    }

}
