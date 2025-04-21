package com.lighting.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.user.model.UserDAO;

@WebServlet("/user/sendmail.do")
public class SendMail extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//SendMail.java
		//1. 데이터 가져오기(email)
		//2. 인증번호 생성
		//3. 이메일 발송
		//3. DB 작업 > insert
		//4. 결과 처리
		
		//1.
		String email = req.getParameter("email");
		/* System.out.println("Email received: " + email); */
		
		//2. 0 ~ 89999 + 10000 > 10000 ~ 99999
		Random rnd = new Random();
		int validNumber = rnd.nextInt(90000) + 10000;
		
		//3.
		MailSender mail = new MailSender();
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", email);
		map.put("validNumber", validNumber + "");
		/* System.out.println("Sending verification code: " + validNumber); */
		
		mail.send(map);
		
		//3.
		UserDAO dao = new UserDAO();
		
		//기존에 처리안된 이메일 > 삭제
		dao.delEmail(email);
		
		//새로운 등록
		dao.addEmail(map);
		dao.close();
		
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print("""
				{
					"result": 1
				}
				""");
		writer.close();		
		
	}



}

