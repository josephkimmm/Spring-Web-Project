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
import com.lighting.meeting.model.MemberDTO;

@WebServlet("/meeting/getmemberinfo.do")
public class GetMemberInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String tblMeetingPostSeq = req.getParameter("tblMeetingPostSeq");
        
        MeetingDAO dao = new MeetingDAO();
        MemberDTO dto = dao.getMemberInfo(tblMeetingPostSeq);
        
        JSONObject obj = new JSONObject();

        dao.close();
        
        if (dto != null) {
            obj.put("tblMemberSeq", dto.getTblMemberSeq());
            obj.put("nickname", dto.getNickname());
            obj.put("gender", dto.getGender());
            obj.put("photoFileName", dto.getPhotoFileName());
            obj.put("score", dto.getScore());
            obj.put("sido", dto.getSido());
            obj.put("gugun", dto.getGugun());
        }
        
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(obj);
        writer.close();

    }

}
