package com.lighting.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.user.model.UserDAO;
import com.lighting.user.model.UserDTO;

@WebServlet("/user/loginok.do")
public class LoginOk extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //LoginOk.java
        
        //1. 데이터 가져오기(id,pw)
        //2. DB 작업
        //3. 인증 티켓 발급
        //4. 결과
        
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        
        UserDAO dao = new UserDAO();
        
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setPw(pw);
        
        UserDTO result = dao.login(dto);
        
        if (result != null) {
            //로그인 성공
            //인증 티켓 발급
            HttpSession session = req.getSession();
            
            session.setAttribute("auth", result.getTblMemberSeq());//인증 티켓

            PrintWriter writer = resp.getWriter();
            writer.print("""
                    <script>
                        alert('welcome!');
                        window.location.href = '/lighting/main.do';
                    </script>
                    """);
            writer.close();
            
        } else {
            //로그인 실패
            PrintWriter writer = resp.getWriter();
            writer.print("""
                    <script>
                        alert('failed to login');
                        history.back();
                    </script>
                    """);
            writer.close();
            
        
        }
        dao.close();
    }
}