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

@WebServlet("/meeting/addok.do")
public class AddOk extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
/*
        사진 작업 보류
        String saveDirectory = req.getRealPath("/images"); // 파일 저장 경로 (webapp/images)
        int maxPostSize = 1024 * 1024 * 10; // 10MB
        String encoding = "UTF-8";
        DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();

        
        MultipartRequest multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, policy);

        String photoFileName = multi.getFilesystemName("photoFileName"); // 서버에 저장된 파일 이름
        File file = multi.getFile("photoFileName"); // File 객체
*/
      // 1. 폼 데이터 받기
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
        String photoFileName = "";
        
        /*
        System.out.println("tblCategorySubSeq: " + tblCategorySubSeq);
        System.out.println("title: " + title);
        System.out.println("startTime: " + startTime);
        System.out.println("capacity: " + capacity);
        System.out.println("location: " + location);
        System.out.println("latitude: " + latitude);
        System.out.println("longitude: " + longitude);
        System.out.println("content: " + content);
        System.out.println("tblMemberSeq: " + tblMemberSeq);
        */
        
        //insert 문 데이터 담기 + 넘기기
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
        
        int result = dao.add(dto); 
        
        if (result == 1) { // meetingpost에 게시글 추가 >> 좌표값도 추가해야함
            //성공
            result = dao.addLocationCoordinate(dto);
            dao.close();
            if (result == 1) {
                resp.sendRedirect("/lighting/main.do");
            } else {
                //실패
                PrintWriter writer = resp.getWriter();
                writer.print("""
                        <script>
                            alert('failed');
                            location.href='/lighting/main.do'
                        </script>
                        """);
                writer.close();
            }
           
        } else {
            //실패
            dao.close();
            PrintWriter writer = resp.getWriter();
            writer.print("""
                    <script>
                        alert('failed');
                        location.href='/lighting/main.do'
                    </script>
                    """);
            writer.close();
        }
        
    }

}
