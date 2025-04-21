package com.lighting.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.user.model.UserDAO;

@WebServlet("/user/validmail.do")
public class ValidMail extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 1. 데이터 가져오기(email, validNumber)
        String email = req.getParameter("email");
        String validNumber = req.getParameter("validNumber");
        //String tel = req.getParameter("tel").replace("-", ""); 
        
        // 전화번호 포맷팅 (예: 01011111111 -> 010-1111-1111)
//        String formattedTel = null;
//        if (tel != null && !tel.isEmpty()) {
//            formattedTel = formatPhoneNumber(tel);
//        } else {
//            formattedTel = ""; // 만약 tel이 null이거나 비어있으면, 빈 문자열로 설정
//        }
        
        System.out.println(email);
        System.out.println(validNumber);
       // System.out.println(tel);
        
        // 2. 이메일과 인증번호를 확인하기 위한 맵 생성
        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("validNumber", validNumber);
        //map.put("tel", tel); 

        UserDAO dao = new UserDAO();
        
        // 3. 인증번호 확인 (DB에서 해당 이메일과 인증번호를 확인)
        int result = dao.validEmail(map);  // result = 1이면 인증 성공
        
        // 4. 인증번호가 맞으면 이메일 삭제 (유효한 인증번호면 이메일을 삭제)
        if (result == 1) {
            dao.delEmail(email);  // 인증 성공 후 이메일 삭제
        }
        
        // 5. 결과 응답
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        PrintWriter writer = resp.getWriter();
        writer.print("""
                {
                    "result": %d
                }
                """.formatted(result));  // 인증 성공 여부 결과를 JSON으로 응답
        writer.close();  
    }
 // 전화번호 포맷팅 메서드
//    private String formatPhoneNumber(String phoneNumber) {
//        // 정규식을 사용하여 전화번호에 "-" 기호를 추가
//        return phoneNumber.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
//    }
}
    


