package com.lighting.meeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.lighting.meeting.model.FriendRequestDTO;
import com.lighting.meeting.model.MeetingDAO;

@WebServlet("/meeting/getfriendrequest.do")
public class GetFriendRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //GetFriendRequest.java
        req.setCharacterEncoding("UTF-8");
        String requestingMemberSeq = req.getParameter("requestingMemberSeq");
        String requestedMemberSeq = req.getParameter("requestedMemberSeq");
        
        FriendRequestDTO dto = new FriendRequestDTO();
        MeetingDAO dao = new MeetingDAO();
        
        dto.setRequestedMemberSeq(requestedMemberSeq);
        dto.setRequestingMemberSeq(requestingMemberSeq);
        JSONObject obj = new JSONObject();
        
        int result = dao.getFriendRequest(dto);
        // 0 오류
        // 1 상대가 나에게 신청
        // 2 내가 상대에게 신청(미응답)
        // 3 이미 친구
        // 4 거절 기록
        // 5 최초 신청
        dao.close();
        
        obj.put("result", result);
        
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(obj);
        writer.close();
        
    }

}
























