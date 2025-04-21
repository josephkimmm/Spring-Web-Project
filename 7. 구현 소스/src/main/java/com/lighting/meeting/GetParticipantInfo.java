package com.lighting.meeting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.lighting.meeting.model.MeetingDAO;
import com.lighting.meeting.model.MemberDTO;

@WebServlet("/meeting/getparticipantinfo.do")
public class GetParticipantInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String tblMeetingPostSeq = req.getParameter("tblMeetingPostSeq");
        
        MeetingDAO dao = new MeetingDAO();
        List<MemberDTO> list = dao.getParticipantInfo(tblMeetingPostSeq);
        
        JSONArray arr = new JSONArray();
        dao.close();
        
        for (MemberDTO dto : list) {
            JSONObject obj = new JSONObject();
            
            obj.put("PhotoFileName", dto.getPhotoFileName());
            obj.put("TblMemberSeq", dto.getTblMemberSeq());
            
            arr.add(obj);
        }
        
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(arr);
        writer.close();
        
        
        
    }

}
