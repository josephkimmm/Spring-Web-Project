package com.lighting.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.user.model.UserDAO;

@WebServlet("/user/findpasswordok.do")
public class FindPasswordOk extends HttpServlet {

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // 사용자 입력 값 받기
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String tel = request.getParameter("tel");

        UserDAO dao = new UserDAO();
        
        // 수정된 findUserPasswordByNameAndIdTel 메서드를 사용하여 비밀번호 조회
        String userPassword = dao.findUserPasswordByNameAndIdTel(name, id, tel); // 비밀번호 반환

        if (userPassword != null) {
            request.setAttribute("userPassword", userPassword);
            request.getRequestDispatcher("/WEB-INF/views/user/findpasswordok.jsp").forward(request, response); // 비밀번호 결과 페이지로 포워드
        } else {
            request.setAttribute("errorMessage", "입력하신 정보와 일치하는 비밀번호가 없습니다.");
            request.getRequestDispatcher("/WEB-INF/views/user/findpassword.jsp").forward(request, response); // 비밀번호가 없으면 다시 입력 페이지로 포워드
        }
    }
	
}