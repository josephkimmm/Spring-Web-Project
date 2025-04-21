package com.lighting.mypage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.lighting.util.DBUtil;

public class MemberDAO {

    private Connection conn;
    private PreparedStatement pstat;
    private ResultSet rs;

    public MemberDAO() {
        try {
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) env.lookup("jdbc/pool");
            conn = ds.getConnection();
            
            System.out.println("DB 연결 성공: " + conn);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (pstat != null) pstat.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ID로 멤버조회
    public MemberDTO getMemberById(String id) {
        
        MemberDTO dto = null;

        try {
            String sql = "SELECT tblMemberSeq, id, nickname, email, photoFileName "
                       + "FROM tblMember WHERE id = ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, id);
            rs = pstat.executeQuery();

            if (rs.next()) {
                dto = new MemberDTO();
                dto.setTblMemberSeq(rs.getInt("tblMemberSeq"));
                dto.setId(rs.getString("id"));
                dto.setNickname(rs.getString("nickname"));
                dto.setEmail(rs.getString("email"));
                dto.setPhotoFileName(rs.getString("photoFileName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }

        return dto;
    }
    //프로필 이미지 변경
    public int updateProfileImage(int memberSeq, String fileName) {
        String sql = "UPDATE tblMember SET photoFileName = ? WHERE tblMemberSeq = ?";

        try {
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, fileName);
            pstat.setInt(2, memberSeq);
            return pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    //멤버 번호로 멤버 조회
    public MemberDTO getMemberBySeq(int memberSeq) {
        MemberDTO dto = null;
        try {
            String sql = "SELECT tblMemberSeq, id, pw, nickname, email, photoFileName FROM tblMember WHERE tblMemberSeq = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, memberSeq);
            rs = pstat.executeQuery();

            if (rs.next()) {
                dto = new MemberDTO();
                dto.setTblMemberSeq(rs.getInt("tblMemberSeq"));
                dto.setId(rs.getString("id"));
                dto.setPw(rs.getString("pw"));
                dto.setNickname(rs.getString("nickname"));
                dto.setEmail(rs.getString("email"));
                dto.setPhotoFileName(rs.getString("photoFileName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
         // ❌ conn은 닫지 말고, pstat, rs만 닫으세요!
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (pstat != null) try { pstat.close(); } catch (Exception e) {}
        }
        return dto;
    }
    //비밀번호 및 활동 지역변경
    public int updatePasswordAndRegion(int memberSeq, String newPw, String sido, String gugun) {
        try {
            conn.setAutoCommit(false); // 트랜잭션 시작

            // 1. 비밀번호 변경
            String sql1 = "UPDATE tblMember SET pw = ? WHERE tblMemberSeq = ?";
            pstat = conn.prepareStatement(sql1);
            pstat.setString(1, newPw);
            pstat.setInt(2, memberSeq);
            int result1 = pstat.executeUpdate();
            pstat.close();

            // 2. 지역 좌표 시퀀스 조회
            String sql2 = "SELECT tblActivityRegionCoordinateSeq FROM tblActivityRegionCoordinate WHERE sido = ? AND gugun = ?";
            pstat = conn.prepareStatement(sql2);
            pstat.setString(1, sido);
            pstat.setString(2, gugun);
            rs = pstat.executeQuery();

            int coordinateSeq = -1;
            if (rs.next()) {
                coordinateSeq = rs.getInt("tblActivityRegionCoordinateSeq");
            } else {
                conn.rollback();
                return 0; // 지역이 존재하지 않으면 실패
            }
            rs.close();
            pstat.close();

            // 3. 활동지역 테이블 업데이트
            String sql3 = "UPDATE tblActivityRegion SET tblActivityRegionCoordinateSeq = ? WHERE tblMemberSeq = ?";
            pstat = conn.prepareStatement(sql3);
            pstat.setInt(1, coordinateSeq);
            pstat.setInt(2, memberSeq);
            int result2 = pstat.executeUpdate();

            conn.commit();
            return (result1 == 1 && result2 == 1) ? 1 : 0;

        } catch (Exception e) {
            try { conn.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            this.close();
        }
        return 0;
    }
    //개인 정보 수정(전화번호, 닉네임)
    public int updateMember(int userSeq, String tel, String nickname) {
        String sql = "UPDATE tblMember SET tel = ?, nickname = ? WHERE tblMemberSeq = ?";
        try {
            pstat = conn.prepareStatement(sql); // 
            pstat.setString(1, tel);
            pstat.setString(2, nickname);
            pstat.setInt(3, userSeq);
            return pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return 0;
    }
    //공개여부 변경
    public int updatePublicStatus(int memberSeq, int status) {
        String sql = "UPDATE tblMember SET status = ? WHERE tblMemberSeq = ?";
        
        try (Connection conn = DBUtil.open();
             PreparedStatement pstat = conn.prepareStatement(sql)) {
            
            pstat.setInt(1, status);
            pstat.setInt(2, memberSeq);
            
            return pstat.executeUpdate(); // 성공 시 1 반환
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0; // 실패
    }
    //승인상태 변경
    public int updateStatus(int memberSeq, int status) {
        int result = 0;

        String sql = "UPDATE tblMember SET status = ? WHERE tblMemberSeq = ?";

        try {
            conn = DBUtil.open();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, status);
            pstat.setInt(2, memberSeq);
            result = pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstat);
        }

        return result;
    }
    //비밀번호 확인
    public String getPassword(int memberSeq) {
        String sql = "SELECT pw FROM tblMember WHERE tblMemberSeq = ?";
        try (Connection conn = DBUtil.open(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setInt(1, memberSeq);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) return rs.getString("pw");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //비밀번호 수정
    public int updatePassword(int memberSeq, String newPassword) {
        String sql = "UPDATE tblMember SET pw = ? WHERE tblMemberSeq = ?";
        try (Connection conn = DBUtil.open(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, newPassword);
            pstat.setInt(2, memberSeq); 
            return pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return 0;
    }

}
