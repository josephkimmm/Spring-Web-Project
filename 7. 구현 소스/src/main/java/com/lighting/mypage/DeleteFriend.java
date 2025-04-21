package com.lighting.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.mypage.model.FriendDAO;

@WebServlet("/mypage/deletefriend.do")
public class DeleteFriend extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        Object userSeqObj = session.getAttribute("auth");

        if (userSeqObj == null) {
            resp.sendRedirect("/lighting/login.do");
            return;
        }

        int userSeq = (userSeqObj instanceof Integer)
            ? (Integer) userSeqObj
            : Integer.parseInt(userSeqObj.toString());
        
        // Ajax 요청으로 전달된 friendId 파라미터를 가져옵니다.
        int friendId = Integer.parseInt(req.getParameter("friendId"));
        
        FriendDAO friendDAO = new FriendDAO();
        boolean result = friendDAO.deleteFriend(userSeq, friendId);
        
        resp.setContentType("application/json;charset=UTF-8");
        if(result) {
            resp.getWriter().write("{\"status\":\"success\"}");
        } else {
            resp.getWriter().write("{\"status\":\"fail\"}");
        }
    }
}
