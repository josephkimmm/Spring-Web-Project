package com.lighting.meeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.meeting.model.MeetingDAO;
import com.lighting.meeting.model.MeetingPostDTO;

@WebServlet("/meeting/updateok.do")
public class UpdateOk extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UpdateOk.java
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        
        //1. 폼 데이터 받기
        String tblCategorySubSeq = req.getParameter("tblCategorySubSeq");
        String title = req.getParameter("title");
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String startTime = date + " " + time;//'YYYY-MM-DD HH24:MM'
        String capacity = req.getParameter("capacity");
        String location = req.getParameter("location");
        String latitude = req.getParameter("latitude");
        String longitude = req.getParameter("longitude");
        String content = req.getParameter("content");
        String tblMemberSeq = req.getParameter("tblMemberSeq");
        String tblMeetingPostSeq = req.getParameter("tblMeetingPostSeq");
        String photoFileName = "";
        
        MeetingDAO dao = new MeetingDAO();
        MeetingPostDTO dto = new MeetingPostDTO();
        
        dto.setTitle(title);
        dto.setContent(content);
        dto.setStartTime(startTime);
        dto.setCapacity(capacity);
        dto.setLocation(location);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setTblCategorySubSeq(tblCategorySubSeq);
        dto.setTblMemberSeq(tblMemberSeq);
        dto.setPhotoFileName(photoFileName);
        dto.setTblMeetingPostSeq(tblMeetingPostSeq);
        
        int result = dao.update(dto);
        
        if (result == 1) {
            //성공
            result = dao.updateLocationCoordinate(dto);
            dao.close();
            System.out.println("좌표값 업데이트 결과" + result);
            if (result == 1) {
                resp.sendRedirect("/lighting/mypage/mypage.do");
                return;
            } else {
                //실패
                PrintWriter writer = resp.getWriter();
                writer.print("""
                        <script>
                            alert('failed');
                            location.href='/lighting/mypage/mypage.do'
                        </script>
                        """);
                writer.close();
                return;
            }
           
        } else {
            //실패
            dao.close();
            PrintWriter writer = resp.getWriter();
            writer.print("""
                    <script>
                        alert('failed');
                        location.href='/lighting/mypage/mypage.do'
                    </script>
                    """);
            writer.close();
            return;
        }
        
    }

}


















