package com.lighting.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lighting.mypage.model.MeetingDAO;

@WebServlet("/mypage/applystatusok.do")
public class ApplyStatusOk extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8"); // ✅ 한글 파라미터 깨짐 방지
    	
        int count = Integer.parseInt(req.getParameter("applicantCount"));
        int meetingPostSeq = Integer.parseInt(req.getParameter("meetingPostSeq"));

        MeetingDAO dao = new MeetingDAO();

        for (int i = 0; i < count; i++) {
            int requestSeq = Integer.parseInt(req.getParameter("seq_" + i));
            String approval = req.getParameter("approval_" + i);
            String reason = req.getParameter("reason_" + i);

            String approvalStatus = null;

            if ("승인".equals(approval)) {
                approvalStatus = "Y";
            } else if ("거부".equals(approval)) {
                approvalStatus = "N";
            }

            System.out.println("requestSeq: " + requestSeq);
            System.out.println("approval: " + approval);
            System.out.println("approvalStatus: " + approvalStatus);
            System.out.println("reason: " + reason);

            // 승인여부 업데이트
            dao.updateApprovalStatus(requestSeq, approvalStatus);

            // 거절일 경우 사유 저장
            if ("거부".equals(approval)) {
                dao.insertOrUpdateRejectionReason(requestSeq, reason);
            }
        }

        resp.sendRedirect("/lighting/mypage/mypage.do?status=requested");
    }
}
