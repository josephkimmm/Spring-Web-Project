package com.lighting.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/logoutok.do")
public class LogoutOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//LogoutOk.java
		//세션 삭제하고 메인으로 보낼것
		//FIXME
	    
	  //싹 삭제하는 방법
        req.getSession().invalidate();//로그아웃
	    resp.sendRedirect("/lighting/main.do");
	}

}