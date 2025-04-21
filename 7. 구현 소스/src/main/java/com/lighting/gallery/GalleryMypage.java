package com.lighting.gallery;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.gallery.model.GalleryDAO;

@WebServlet("/gallery/gallerymypage.do")
public class GalleryMypage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		GalleryDAO dao = new GalleryDAO();

		HttpSession session = req.getSession();
		String tblMemberSeq = (String) session.getAttribute("auth");
		
		// 사진 파일 이름을 가져오는 메서드 호출
		List<String> photoFilenames = dao.getMyPhotoFilenames(tblMemberSeq); // 리스트를 가져옵니다.

		req.setAttribute("photoFilenames", photoFilenames); // JSP에서 사용할 수 있도록 속성 설정

		req.getRequestDispatcher("/WEB-INF/views/gallery/gallerymypage.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 삭제할 파일명을 배열로 받기
		//String[] filenames = req.getParameterValues("filenames");

		HttpSession session = req.getSession();
		String tblMemberSeq = (String) session.getAttribute("auth");
		GalleryDAO dao = new GalleryDAO();
		
		if (tblMemberSeq != null) {
			
			int deletedCount = dao.deletePhotos(tblMemberSeq);
			dao.close();

			System.out.println(deletedCount + "개의 사진이 삭제되었습니다.");
		}

		// 삭제 후 목록 페이지로 리다이렉트
		resp.sendRedirect("gallerymypage.do");
	}
}