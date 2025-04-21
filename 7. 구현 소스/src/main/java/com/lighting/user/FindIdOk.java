package com.lighting.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.user.model.UserDAO;

@WebServlet("/user/findidok.do")
public class FindIdOk extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // 사용자 입력 값 받기
        String name = request.getParameter("name");
        String tel = request.getParameter("tel"); // 'contact'를 'tel'로 변경

        UserDAO dao = new UserDAO();
        
        // 수정된 findUserIdByNameAndContact 메서드를 사용하여 아이디 조회
        String userId = dao.findUserIdByNameAndTel(name, tel); // 'contact'가 아닌 'tel' 사용

        if (userId != null) {
            request.setAttribute("userId", userId);
            request.getRequestDispatcher("/WEB-INF/views/user/findidok.jsp").forward(request, response); // 아이디 결과 페이지로 포워드
        } else {
            request.setAttribute("errorMessage", "입력하신 정보와 일치하는 아이디가 없습니다.");
            request.getRequestDispatcher("/WEB-INF/views/user/findid.jsp").forward(request, response); // 아이디가 없으면 다시 입력 페이지로 포워드
        }
    }
}