package com.lighting.mypage.model;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class EvaluationDAO {

    private Connection conn;
    private PreparedStatement pstat;
    private ResultSet rs;

    public EvaluationDAO() {
        try {
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) env.lookup("jdbc/pool");
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (pstat != null) pstat.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getAverageScore(int evaluatedMemberSeq) {
        double avg = 0;
        try {
            String sql = "SELECT NVL(AVG(score), 0) AS avgScore FROM tblEvaluation WHERE evaluatedMemberSeq = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, evaluatedMemberSeq);
            rs = pstat.executeQuery();

            if (rs.next()) {
                avg = rs.getDouble("avgScore");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return avg;
    }
}
