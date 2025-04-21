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

import com.lighting.meeting.model.ActivityRegionCoordinateDTO;
import com.lighting.meeting.model.MeetingDAO;

@WebServlet("/meeting/getactivityregioncoordinate.do")
public class GetActivityRegionCoordinate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String tblMemberSeq = req.getParameter("tblMemberSeq");
        MeetingDAO dao = new MeetingDAO();
        ActivityRegionCoordinateDTO dto = dao.getActivityRegionCoordinate(tblMemberSeq);
        dao.close();
        
        JSONObject obj = new JSONObject();
        
        if (dto != null) {
            obj.put("latitude", dto.getLatitude());
            obj.put("longitude", dto.getLongitude());
        }
        
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        PrintWriter writer = resp.getWriter();
        writer.print(obj);
        writer.close();
    }

}