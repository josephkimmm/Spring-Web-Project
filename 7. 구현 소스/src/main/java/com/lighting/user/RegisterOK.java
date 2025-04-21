package com.lighting.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/registerok.do")
public class RegisterOK extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* 
    	// 인코딩 설정
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        try {
            // 회원 정보 파라미터 읽기
            String id = req.getParameter("id");
            System.out.println(id);
            String pw = req.getParameter("pw");
            String name = req.getParameter("name");
            String nickname = req.getParameter("nickname");
            String birthday = req.getParameter("birthday");
            String tel = req.getParameter("tel");
            String email = req.getParameter("email");
            String gender = req.getParameter("gender");
            String city = req.getParameter("city");
            String district = req.getParameter("district");
            

            System.out.println(birthday);
            
            // 회원 정보 DTO에 값 설정
            MemberDTO memberDto = new MemberDTO();
            memberDto.setId(id);
            memberDto.setPw(pw);
            memberDto.setName(name);
            memberDto.setNickname(nickname);
            memberDto.setBirthday(birthday);
            memberDto.setTel(tel);
            memberDto.setEmail(email);
            memberDto.setGender(gender);
            memberDto.setSido(city);
            memberDto.setGugun(district);

            MemberDAO mdao = new MemberDAO(); 
            //아이디 중복 검사
            if(mdao.isDuplicateId(id)) {
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('중복된 아이디입니다. 다른 아이디를 선택해 주세요.'); history.back();</script>");
                writer.close();
                return;
            }
            
            
            // DAO를 통해 회원정보 저장 및 회원 시퀀스 반환 받기
            int memberSeq = mdao.addMember(memberDto);
            System.out.println(memberSeq);
            // 회원가입 후, 선택한 주소에 해당하는 활동지역 시퀀스 가져오기
            int regionSeq = mdao.addActivityRegionCoordinate(memberDto);
            System.out.println(regionSeq);
            
            
            
            ActivityRegionDTO arDto = new ActivityRegionDTO();
            arDto.setTblMemberSeq(memberSeq);
            arDto.setTblActivityRegionCoordinateSeq(regionSeq);
            
            ActivityRegionDAO ardao = new ActivityRegionDAO();
            int insertResult = ardao.insertActivityRegion(arDto);
            
            // DAO 자원 해제
            mdao.close();
            ardao.close();

            // 회원가입 성공 여부에 따른 처리
            if (memberSeq >0 && regionSeq >0 && insertResult == 1) {
                resp.sendRedirect("/loginFinish.jsp");
            } else {
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('회원가입에 실패했습니다.'); history.back();</script>");
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('회원가입 처리 중 오류가 발생했습니다.'); history.back();</script>");
            writer.close();*/
    	
    	req.getRequestDispatcher("/WEB-INF/views/user/registerfinish.jsp").forward(req, resp);
      }
  }
