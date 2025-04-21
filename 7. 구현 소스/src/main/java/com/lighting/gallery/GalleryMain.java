package com.lighting.gallery;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.gallery.model.GalleryDAO;

@WebServlet("/gallery/gallerymain.do")
public class GalleryMain extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		GalleryDAO dao = new GalleryDAO();

		// 사진 파일 이름을 가져오는 메서드 호출
		List<String> photoFilenames = dao.getAllPhotoFilenames(); // 리스트를 가져옵니다.

		req.setAttribute("photoFilenames", photoFilenames); // JSP에서 사용할 수 있도록 속성 설정

		req.getRequestDispatcher("/WEB-INF/views/gallery/gallerymain.jsp").forward(req, resp);
	}
}