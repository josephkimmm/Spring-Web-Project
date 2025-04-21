package com.lighting.user.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.user.model.UserDAO;
import com.lighting.user.model.UserDTO;

@WebServlet("/user/register/registerok.do")
public class RegisterOK extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//RegisterOK.java
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
		
		
		try {
			// 회원 정보 파라미터 읽기
            String id = req.getParameter("id");
            String pw = req.getParameter("pw");
            String name = req.getParameter("name");
            String nickname = req.getParameter("nickname");
            String birthday = req.getParameter("birthday");
            String tel = req.getParameter("tel");
            String email = req.getParameter("email").replace("-", "");
            String gender = req.getParameter("gender");
            String sido = req.getParameter("sido");  // 시도
            String gugun = req.getParameter("gugun");  // 구군
            
			/* UserDAO dao=new UserDAO(); */
            
			/*
			 * // 아이디 중복 체크 if (dao.isDuplicateId(id)) { // 중복된 아이디일 경우, 중복 메시지를 request에
			 * 저장하고 register.jsp로 포워드 req.setAttribute("idError",
			 * "아이디가 중복됩니다. 다른 아이디를 사용해주세요.");
			 * req.getRequestDispatcher("/WEB-INF/views/lighting/user/register/register.jsp"
			 * ).forward(req, resp); return; // 중복된 아이디일 경우 더 이상 진행하지 않음 }
			 */
            
            // 아이디 중복되지 않으면 회원 가입 진행
            UserDTO dto= new UserDTO();
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			dto.setNickname(nickname);
			dto.setBirthday(birthday);
			dto.setTel(tel);
			dto.setEmail(email);
			dto.setGender(gender);
			
			dto.setSido(sido);  // 시도 정보 추가
            dto.setGugun(gugun);  // 구군 정보 추가
			
			/* int result=dao.addUser(dto); */
         // UserDAO 객체 생성하여 DB에 저장
            UserDAO dao = new UserDAO();
            int addUserResult = dao.addUser(dto);  // 회원 정보 DB에 저장

            //***
            //1. 회원seq > select 
            //2. 주소seq
            //3. insert


            if (addUserResult == 1) {
                // 회원seq 가져오기
                int memberSeq = dao.getLastInsertedMemberSeq();  // 회원seq를 가져오는 메서드를 추가해야 합니다.
                System.out.println("memberSeq: " + memberSeq);

                // 활동지역 좌표seq 가져오기 (시도와 구군으로)
                int activityRegionCoordinateSeq = dao.getActivityRegionCoordinateSeq(sido, gugun);  // sido, gugun으로 좌표seq 가져오기
                System.out.println("activityRegionCoordinateSeq: " + activityRegionCoordinateSeq);

                // 회원과 활동지역 연결
                dao.addActivityRegion(memberSeq, activityRegionCoordinateSeq);  // tblActivityRegion에 데이터 추가

                req.getRequestDispatcher("/WEB-INF/views/user/register/registerfinish.jsp").forward(req, resp);
            } else {
                req.setAttribute("errorMessage", "회원가입에 실패했습니다.");
                req.getRequestDispatcher("/WEB-INF/views/user/register/register.jsp").forward(req, resp);
            }

            
            dao.close();
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "회원가입 처리 중 오류가 발생했습니다.");
            req.getRequestDispatcher("/WEB-INF/views/user/register/register.jsp").forward(req, resp);
        }
    }
}
            
            


