package com.lighting.mypage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/mypage/cancelRequest.do")
public class CancelRequest extends HttpServlet {

    private DataSource getDataSource() throws NamingException {
         InitialContext ctx = new InitialContext();
         return (DataSource) ctx.lookup("java:comp/env/jdbc/pool");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        // 세션에서 로그인된 사용자 번호 가져오기
        Integer userSeq = (Integer) req.getSession().getAttribute("auth");
        if (userSeq == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("{\"status\":\"unauthorized\"}");
            return;
        }

        int requestSeq;
        try {
            requestSeq = Integer.parseInt(req.getParameter("requestSeq"));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"status\":\"invalid_request_seq\"}");
            return;
        }

        String sql = "DELETE FROM tblParticipationRequest WHERE tblParticipationRequestSeq = ? AND memberSeq = ?";

        try (Connection conn = getDataSource().getConnection();
             PreparedStatement stat = conn.prepareStatement(sql)) {

            stat.setInt(1, requestSeq);
            stat.setInt(2, userSeq);

            int result = stat.executeUpdate();

            resp.setContentType("application/json;charset=UTF-8");
            if (result > 0) {
                resp.getWriter().write("{\"status\":\"success\"}");
            } else {
                resp.getWriter().write("{\"status\":\"fail\"}");
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write("{\"status\":\"error\"}");
        }
    }
}
