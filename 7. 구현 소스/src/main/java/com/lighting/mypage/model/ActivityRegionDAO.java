package com.lighting.mypage.model;

import java.sql.*;
import com.lighting.util.DBUtil;

public class ActivityRegionDAO {

    public int getCoordinateSeq(String sido, String gugun) {
        String sql = "SELECT tblActivityRegionCoordinateSeq FROM tblActivityRegionCoordinate WHERE sido = ? AND gugun = ?";
        try (Connection conn = DBUtil.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sido);
            pstmt.setString(2, gugun);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("tblActivityRegionCoordinateSeq");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int updateActivityRegion(int memberSeq, int regionSeq) {
        // 기존 활동지역이 있는지 확인 후 update or insert
        String checkSql = "SELECT COUNT(*) AS cnt FROM tblActivityRegion WHERE tblMemberSeq = ?";
        String updateSql = "UPDATE tblActivityRegion SET tblActivityRegionCoordinateSeq = ? WHERE tblMemberSeq = ?";
        String insertSql = "INSERT INTO tblActivityRegion (tblMemberSeq, tblActivityRegionCoordinateSeq) VALUES (?, ?)";

        try (Connection conn = DBUtil.open();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, memberSeq);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("cnt") > 0) {
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, regionSeq);
                    updateStmt.setInt(2, memberSeq);
                    return updateStmt.executeUpdate();
                }
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, memberSeq);
                    insertStmt.setInt(2, regionSeq);
                    return insertStmt.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String[] getSidoGugunByMemberSeq(int userSeq) {
        String sql = "SELECT c.sido, c.gugun FROM tblActivityRegion r " +
                     "JOIN tblActivityRegionCoordinate c ON r.tblActivityRegionCoordinateSeq = c.tblActivityRegionCoordinateSeq " +
                     "WHERE r.tblMemberSeq = ?";
        try (Connection conn = DBUtil.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userSeq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new String[] { rs.getString("sido"), rs.getString("gugun") };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[] { "", "" };
    }
}