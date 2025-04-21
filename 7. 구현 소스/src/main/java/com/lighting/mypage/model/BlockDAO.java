package com.lighting.mypage.model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BlockDAO {
    private Connection conn;
    private PreparedStatement pstat;
    private ResultSet rs;

    public BlockDAO() {
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

    // ✅ 1. 차단 목록 조회
    public ArrayList<BlockDTO> getBlockList(int userSeq) {
        /* System.out.println("getBlockList 메서드 호출됨: userSeq = " + userSeq); */
        ArrayList<BlockDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT b.blockedMemberSeq, m.nickname " +
                         "FROM tblBlockList b " +
                         "JOIN tblMember m ON b.blockedMemberSeq = m.tblMemberSeq " +
                         "WHERE b.blockerMemberSeq = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, userSeq);
            rs = pstat.executeQuery();

            while (rs.next()) {
                BlockDTO dto = new BlockDTO();
                dto.setBlockedMemberSeq(rs.getInt("blockedMemberSeq"));
                dto.setNickname(rs.getString("nickname"));
                list.add(dto);
                
                /* System.out.println("쿼리 결과: " + dto); */
            }
        } catch (Exception e) {
            /* System.out.println("쿼리 실행 중 오류 발생: " + e.getMessage()); */
            e.printStackTrace();
        } finally {
            /* System.out.println("getBlockList 메서드 종료, 반환 리스트 크기: " + list.size()); */
            close();
        }
        return list;
    }

    // ✅ 2. 차단 해제 (삭제)
    public boolean unblockUser(int userSeq, int blockedId) {
        boolean result = false;
        try {
            String sql = "DELETE FROM tblBlockList WHERE blockerMemberSeq = ? AND blockedMemberSeq = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, userSeq);
            pstat.setInt(2, blockedId);
            int res = pstat.executeUpdate();
            result = res > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
}
