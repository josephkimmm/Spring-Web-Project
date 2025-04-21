package com.lighting.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.mypage.model.BlockDAO;
import com.lighting.mypage.model.BlockDTO;
import com.lighting.mypage.model.EvaluationDAO;
import com.lighting.mypage.model.FriendDAO;
import com.lighting.mypage.model.FriendDTO;
import com.lighting.mypage.model.MeetingDAO;
import com.lighting.mypage.model.MeetingDTO;
import com.lighting.mypage.model.MemberDAO;
import com.lighting.mypage.model.MemberDTO;

@WebServlet("/mypage/mypage.do")
public class Mypage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String status = req.getParameter("status");
        if (status == null) status = "joined";

        String sort = req.getParameter("sort");
        if (sort == null) sort = "recentOrder";

        // ✅ 세션에서 사용자 번호 가져오기
        HttpSession session = req.getSession();
        String userSeqStr = (String) session.getAttribute("auth");
        if (userSeqStr == null) {
            resp.sendRedirect("/lighting/user/login.do");
            return;
        }
        int userSeq = Integer.parseInt(userSeqStr);



        // 2. 세션에 닉네임/아이디 저장이 안 되어 있다면 설정 (1회성)
        if (session.getAttribute("userNickname") == null) {
            MemberDAO memberDAO = new MemberDAO();
            MemberDTO memberInfo = memberDAO.getMemberBySeq(userSeq);
            memberDAO.close();
            if (memberInfo != null) {
                session.setAttribute("userNickname", memberInfo.getNickname());
                session.setAttribute("userId", memberInfo.getId());
                session.setAttribute("evaluationIcon", "마스터.png"); // 점수 기반이 아니므로 참고용
            }
        }

        // 3. 모임 목록 조회
        MeetingDAO meetingDAO = new MeetingDAO();
        ArrayList<MeetingDTO> meetingList;

        switch (status) {
            case "joined":
                meetingList = meetingDAO.getJoinedMeetings(userSeq, sort);
                break;
            case "written":
                meetingList = meetingDAO.getWrittenMeetings(userSeq, sort);
                break;
            case "wish":
                meetingList = meetingDAO.getWishMeetings(userSeq, sort);
                break;
            case "requesting":
                meetingList = meetingDAO.getRequestingMeetings(userSeq, sort);
                break;
            case "requested":
                meetingList = meetingDAO.getRequestedMeetings(userSeq, sort);
                break;
            default:
                meetingList = meetingDAO.getJoinedMeetings(userSeq, sort);
        }
        meetingDAO.close();

        req.setAttribute("meetingList", meetingList);
        req.setAttribute("status", status);
        req.setAttribute("sort", sort);

        // 4. 친구 목록
        FriendDAO friendDAO = new FriendDAO();
        ArrayList<FriendDTO> friendList = friendDAO.getFriendList(userSeq);
        friendDAO.close();
        req.setAttribute("friendList", friendList);

        // 5. 차단 목록
        BlockDAO blockDAO = new BlockDAO();
        ArrayList<BlockDTO> blockList = blockDAO.getBlockList(userSeq);
        blockDAO.close();
        req.setAttribute("blockList", blockList);

        // 6. 회원 기본 정보
        MemberDAO memberDAO = new MemberDAO();
        MemberDTO member = memberDAO.getMemberBySeq(userSeq);
        memberDAO.close();
        
        req.setAttribute("member", member);

        // 7. 평가 점수 및 등급 아이콘
        EvaluationDAO evaDAO = new EvaluationDAO();
        double avgScore = evaDAO.getAverageScore(userSeq);
        evaDAO.close();

        String gradeIcon = "실버.png";
        if (avgScore >= 4) gradeIcon = "마스터.png";
        else if (avgScore >= 3) gradeIcon = "다이아.png";
        else if (avgScore >= 2) gradeIcon = "골드.png";

        req.setAttribute("avgScore", String.format("%.1f", avgScore));
        req.setAttribute("gradeIcon", gradeIcon);

        // 8. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp").forward(req, resp);
    }
}
