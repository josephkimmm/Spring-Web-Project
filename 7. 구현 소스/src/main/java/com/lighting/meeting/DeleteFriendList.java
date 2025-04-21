package com.lighting.meeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.meeting.model.FriendListDTO;
import com.lighting.meeting.model.MeetingDAO;

@WebServlet("/meeting/deletefriendlist.do")
public class DeleteFriendList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        String mainMemberSeq = req.getParameter("mainMemberSeq");
        String subMemberSeq = req.getParameter("subMemberSeq");
        
        FriendListDTO dto = new FriendListDTO();
        MeetingDAO dao = new MeetingDAO();
        
        dto.setMainMemberSeq(mainMemberSeq);
        dto.setSubMemberSeq(subMemberSeq);
        
        int result = dao.deleteFriendList(dto);
        if (result == 3) {
            System.out.println("친구 삭제 성공");
        }
        dao.close();
        
    }

}
