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

import com.lighting.meeting.model.CategorySubDTO;
import com.lighting.meeting.model.MeetingDAO;

@WebServlet("/meeting/getcategorysub.do")
public class GetCategorySub extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String tblCategoryMainSeq = req.getParameter("tblCategoryMainSeq");
        
        MeetingDAO dao = new MeetingDAO();
        List<CategorySubDTO> list = dao.getCategories(tblCategoryMainSeq);
        dao.close();
        
        JSONArray arr = new JSONArray();
        
        for(CategorySubDTO dto : list) {
            JSONObject obj = new JSONObject();
            obj.put("tblCategorySubSeq", dto.getTblCategorySubSeq());
            obj.put("categoryName", dto.getCategoryName());
            
            arr.add(obj);
        }
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        PrintWriter writer = resp.getWriter();
        writer.print(arr);
        writer.close();
    }
}
