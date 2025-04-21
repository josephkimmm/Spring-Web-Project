package com.lighting.mypage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.lighting.util.DBUtil;

public class RejectionDAO {

    public RejectionInfoDTO getRejectionInfo(int requestSeq) {
        RejectionInfoDTO dto = null;
        String sql = "SELECT m.nickname, p.title, r.reason " +
                     "FROM tblRejectionReason r " +
                     "JOIN tblParticipationRequest pr ON r.tblParticipationRequestSeq = pr.tblParticipationRequestSeq " +
                     "JOIN tblMember m ON pr.tblMemberSeq = m.tblMemberSeq " +
                     "JOIN tblMeetingPost p ON pr.tblMeetingPostSeq = p.tblMeetingPostSeq " +
                     "WHERE r.tblParticipationRequestSeq = ?";

        try (Connection conn = DBUtil.open();
             PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setInt(1, requestSeq);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                dto = new RejectionInfoDTO();
                dto.setNickname(rs.getString("nickname"));
                dto.setTitle(rs.getString("title"));
                dto.setReason(rs.getString("reason"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            
            
        }
        return dto;
    }
}
