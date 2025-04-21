package com.lighting.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/mypage/evaluationok.do")
public class GiveEvaluationOk extends HttpServlet {

    private DataSource getDataSource() throws NamingException {
        InitialContext ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/pool");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("auth") == null) {
            out.write("{\"result\":\"fail\", \"reason\":\"unauthorized\"}");
            out.flush();
            out.close();
            return;
        }

        int evaluator;
        try {
            Object authObj = session.getAttribute("auth");
            evaluator = (authObj instanceof Integer) ? (Integer) authObj : Integer.parseInt(authObj.toString());
        } catch (Exception e) {
            out.write("{\"result\":\"fail\", \"reason\":\"invalid_session\"}");
            out.flush();
            out.close();
            return;
        }

        int meetingSeq;
        int count;

        try {
            meetingSeq = Integer.parseInt(req.getParameter("meetingSeq"));
            count = Integer.parseInt(req.getParameter("evaluationCount"));
        } catch (NumberFormatException e) {
            out.write("{\"result\":\"fail\", \"reason\":\"invalid_input\"}");
            out.flush();
            out.close();
            System.out.println(111);
            return;
        }
        
        System.out.println(333);

        try (Connection conn = getDataSource().getConnection();
             PreparedStatement pstat = conn.prepareStatement(
                 "INSERT INTO tblEvaluation (tblMeetingSeq, evaluatorMemberSeq, evaluatedMemberSeq, score) VALUES (?, ?, ?, ?)")
        ) {
        	System.out.println(222);
            for (int i = 0; i < count; i++) {
                String scoreStr = req.getParameter("rating_" + i);
                String userId = req.getParameter("userId_" + i);
                if (scoreStr == null || userId == null) continue;

                int score = Integer.parseInt(scoreStr);
                int targetSeq = getMemberSeqById(userId, conn);
                if (targetSeq == -1) continue;

                pstat.setInt(1, meetingSeq);
                pstat.setInt(2, evaluator);
                pstat.setInt(3, targetSeq);
                pstat.setInt(4, score);
                pstat.addBatch();
            }
            pstat.executeBatch();

            out.write("{\"result\":\"success\"}");
            out.flush();
            out.close();

        } catch (Exception e) {
        	
        	
            e.printStackTrace(); // 실제 운영 시 로그 처리 권장
            out.write("{\"result\":\"fail\", \"reason\":\"db_error\"}");
            out.flush();
            out.close();
        }
    }

    private int getMemberSeqById(String id, Connection conn) throws SQLException {
        String sql = "SELECT tblMemberSeq FROM tblMember WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("tblMemberSeq") : -1;
        }
    }
}
