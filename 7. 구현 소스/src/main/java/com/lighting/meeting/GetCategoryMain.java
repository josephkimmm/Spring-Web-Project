package com.lighting.meeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.lighting.meeting.model.CategoryMainDTO;
import com.lighting.meeting.model.MeetingDAO;

@WebServlet("/meeting/getcategorymain.do")
public class GetCategoryMain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //GetCategoryMain.java
        req.setCharacterEncoding("UTF-8");
        String tblCategorySubSeq = req.getParameter("tblCategorySubSeq");
        
        MeetingDAO dao = new MeetingDAO();
        CategoryMainDTO dto = dao.getCategoryMain(tblCategorySubSeq);
        dao.close();
        
        JSONObject obj = new JSONObject();
        
        if (dto != null) {
            obj.put("tblCategoryMainSeq", dto.getTblCategoryMainSeq());
            obj.put("categoryName", dto.getCategoryName());
        }
        
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(obj);
        writer.close();
        
    }

}





















