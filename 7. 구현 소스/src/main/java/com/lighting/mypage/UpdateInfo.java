package com.lighting.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lighting.mypage.model.ActivityRegionDAO;
import com.lighting.mypage.model.MemberDAO;
import com.lighting.mypage.model.MemberDTO;

@WebServlet("/mypage/updateinfo.do")
public class UpdateInfo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    HttpSession session = req.getSession();
	    Object userSeqObj = session.getAttribute("auth");

	    if (userSeqObj == null) {
	        resp.sendRedirect("/lighting/login.do");
	        return;
	    }

	    int userSeq = (userSeqObj instanceof Integer)
	        ? (Integer) userSeqObj
	        : Integer.parseInt(userSeqObj.toString());

	    MemberDAO memberDAO = new MemberDAO();
	    MemberDTO member = memberDAO.getMemberBySeq(userSeq);
	    req.setAttribute("member", member);

	    // 활동지역 (시도, 구군) 가져오기
	    ActivityRegionDAO regionDAO = new ActivityRegionDAO();
	    String[] region = regionDAO.getSidoGugunByMemberSeq(userSeq); // {"서울특별시", "강남구"}
	    req.setAttribute("sido", region[0]);
	    req.setAttribute("gugun", region[1]);

	    req.getRequestDispatcher("/WEB-INF/views/mypage/updateinfo.jsp").forward(req, resp);
	}
}
