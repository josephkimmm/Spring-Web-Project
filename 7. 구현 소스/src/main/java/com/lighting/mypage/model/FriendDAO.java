package com.lighting.mypage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FriendDAO {
    private Connection conn;
    private PreparedStatement pstat;
    private ResultSet rs;

    public FriendDAO() {
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
            if (rs != null)
                rs.close();
            if (pstat != null)
                pstat.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 친구 목록 조회: 현재 사용자(mainMemberSeq)에 해당하는 모든 친구(subMemberSeq)를 조회
    public ArrayList<FriendDTO> getFriendList(int userSeq) {
        System.out.println("getFriendList 메서드 호출됨: userSeq = " + userSeq);
        ArrayList<FriendDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT f.subMemberSeq, m.nickname " + "FROM tblFriendList f "
                    + "JOIN tblMember m ON f.subMemberSeq = m.tblMemberSeq " + "WHERE f.mainMemberSeq = ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, userSeq);
            rs = pstat.executeQuery();
            while (rs.next()) {
                FriendDTO friend = new FriendDTO();
                friend.setFriendMemberSeq(rs.getInt("subMemberSeq"));
                friend.setNickname(rs.getString("nickname"));
                list.add(friend);

                
                 System.out.println("쿼리 결과: " + friend); // 추가됨
                 
            }
        } catch (Exception e) {
            System.out.println("쿼리 실행 중 오류 발생: " + e.getMessage()); 
            e.printStackTrace();
        } finally {
            System.out.println("getFriendList 메서드 종료, 반환 리스트 크기: " + list.size());
            close();
        }
        return list;
    }

    // 친구 삭제: mainMemberSeq와 subMemberSeq에 해당하는 친구 관계를 삭제합니다.
    public boolean deleteFriend(int userSeq, int friendId) {
        boolean result = false;
        try {
            String sql = "DELETE FROM tblFriendList WHERE mainMemberSeq = ? AND subMemberSeq = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, userSeq);
            pstat.setInt(2, friendId);
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
