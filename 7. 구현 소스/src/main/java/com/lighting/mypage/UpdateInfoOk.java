package com.lighting.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lighting.mypage.model.ActivityRegionDAO;
import com.lighting.mypage.model.MemberDAO;
import com.lighting.mypage.model.MemberDTO;

@WebServlet("/mypage/updateinfook.do")
public class UpdateInfoOk extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        // ✅ 로그인된 사용자 세션에서 userSeq 가져오기
        HttpSession session = req.getSession();
        Object userSeqObj = session.getAttribute("auth");

        if (userSeqObj == null) {
            resp.sendRedirect("/lighting/user/login.do");
            return;
        }

        int userSeq = (userSeqObj instanceof Integer)
            ? (Integer) userSeqObj
            : Integer.parseInt(userSeqObj.toString());

        String tel = req.getParameter("tel");
        String nickname = req.getParameter("nickname");
        String sido = req.getParameter("sido");
        String gugun = req.getParameter("gugun");

        // 기존 회원 정보 조회
        MemberDAO memberDAO = new MemberDAO();
        MemberDTO member = memberDAO.getMemberBySeq(userSeq);

        if (tel == null || tel.trim().isEmpty()) {
            tel = member.getTel(); // 기존 전화번호 유지
        }

        if (nickname == null || nickname.trim().isEmpty()) {
            nickname = member.getNickname(); // 기존 닉네임 유지
        }

        // 회원 정보 업데이트
        memberDAO.updateMember(userSeq, tel, nickname);

        // 활동 지역 좌표 시퀀스 조회 및 업데이트
        ActivityRegionDAO regionDAO = new ActivityRegionDAO();
        int regionSeq = regionDAO.getCoordinateSeq(sido, gugun);
        if (regionSeq != -1) {
            regionDAO.updateActivityRegion(userSeq, regionSeq);
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(
            "<!DOCTYPE html>" +
            "<html><head><meta charset='UTF-8'>" +
            "<script>alert('개인정보가 성공적으로 수정되었습니다.'); window.close();</script>" +
            "</head><body></body></html>"
        );
    }
}
