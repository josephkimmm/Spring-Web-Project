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
import com.lighting.meeting.model.MeetingPostDTO;

@WebServlet("/meeting/getpostinfo.do")
public class GetPostInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String tblMeetingPostSeq = req.getParameter("tblMeetingPostSeq");
        
        MeetingDAO dao = new MeetingDAO();
        MeetingPostDTO dto = dao.getPostInfo(tblMeetingPostSeq);
        JSONObject obj = new JSONObject();

        dao.close();
        
        if (dto != null) {
            obj.put("Title", dto.getTitle());
            obj.put("Content", dto.getContent());
            obj.put("PostDate", dto.getPostDate());
            obj.put("Location", dto.getLocation());
            obj.put("Capacity", dto.getCapacity());
            obj.put("StartTime", dto.getStartTime());
            obj.put("EndTime", dto.getEndTime());
            obj.put("PhotoFileName", dto.getPhotoFileName());
            obj.put("Latitude", dto.getLatitude());
            obj.put("Longitude", dto.getLongitude());
            obj.put("TblCategorySubSeq", dto.getTblCategorySubSeq());
        }
        
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(obj);
        writer.close();
        
    }

}
