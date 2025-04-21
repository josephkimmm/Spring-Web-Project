package com.lighting.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/writingPost.do")
public class WritingPost extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//WritingPost.java
		

		req.getRequestDispatcher("/WEB-INF/views/writingPost.jsp").forward(req, resp);
	}

}

