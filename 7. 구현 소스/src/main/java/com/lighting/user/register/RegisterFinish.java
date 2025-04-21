package com.lighting.user.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/register/registerfinish.do")
public class RegisterFinish extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//RegisterFinish.java
		//보류

		req.getRequestDispatcher("/WEB-INF/views/user/register/registerfinish.jsp").forward(req, resp);
	}

}
