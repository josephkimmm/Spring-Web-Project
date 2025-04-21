package com.lighting.meeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.lighting.meeting.model.MeetingDAO;
import com.lighting.meeting.model.WishlistDTO;

@WebServlet("/meeting/getwish.do")
public class GetWish extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //GetWish.java
        req.setCharacterEncoding("UTF-8");
        String tblMeetingPostSeq = req.getParameter("tblMeetingPostSeq");
        String loginedtblMemberSeq = req.getParameter("loginedtblMemberSeq");
        
        WishlistDTO dto = new WishlistDTO();
        MeetingDAO dao = new MeetingDAO();
        JSONObject obj = new JSONObject();
        
        dto.setTblMeetingPostSeq(tblMeetingPostSeq);
        dto.setTblMemberSeq(loginedtblMemberSeq);
        
        int result = dao.getWish(dto);
        dao.close();
        
        obj.put("result", result);
        
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(obj);
        writer.close();
        
    }

}


















