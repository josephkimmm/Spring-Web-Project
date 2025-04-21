package com.lighting.meeting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.meeting.model.CategoryMainDTO;
import com.lighting.meeting.model.MeetingDAO;
import com.lighting.meeting.model.MeetingPostDTO;
import com.lighting.meeting.model.MemberDTO;

@WebServlet("/meeting/update.do")
public class Update extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Update.java
        HttpSession session = req.getSession();
        String tblMemberSeq = (String) session.getAttribute("auth");
        String tblMeetingPostSeq = req.getParameter("tblMeetingPostSeq");
        
        MeetingDAO dao = new MeetingDAO();
        MeetingPostDTO postdto = new MeetingPostDTO();
        List<MemberDTO> chekcList = dao.getParticipantInfo(tblMeetingPostSeq);
        int checkMeeting = dao.getMeetingInfo(tblMeetingPostSeq);
        
        if (checkMeeting == 1) {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print("""
                    <script>
                        alert('모임이 성사되어 수정이 불가능합니다.');
                        location.href = '/lighting/mypage/mypage.do';
                    </script>
                    """);
            writer.close();
            return; 
        }
        
        if (chekcList == null || !chekcList.isEmpty()) {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print("""
                    <script>
                        alert('참가자가 있는 게시글은 수정이 불가능합니다.');
                        location.href = '/lighting/mypage/mypage.do';
                    </script>
                    """);
            writer.close();
            return; 
        }
        
        postdto = dao.getPostInfo(tblMeetingPostSeq);
        
        if (!tblMemberSeq.equals(postdto.getTblMemberSeq())) {//로그인 != 작성자
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print("""
                    <script>
                        location.href = '/lighting/main.do';
                    </script>
                    """);
            writer.close();
            return;
        }
        
        CategoryMainDTO categorydto = dao.getCategoryMain(postdto.getTblCategorySubSeq());
        List<MeetingPostDTO> list = dao.getMeetingInfoList(tblMemberSeq);
       
        dao.close();
        
        //2025-03-01 11:11:11
        //0123456789 
        String date = postdto.getStartTime().substring(0,10);
        String time = postdto.getStartTime().substring(11,16);
        String content = postdto.getContent();
        content = content.replace("\r\n", "\n");
        
        req.setAttribute("list", list);
        req.setAttribute("content", content);
        req.setAttribute("date", date);
        req.setAttribute("time", time);
        req.setAttribute("categorydto", categorydto);
        req.setAttribute("postdto", postdto);
        req.getRequestDispatcher("/WEB-INF/views/meeting/update.jsp").forward(req, resp);
    }

}




















