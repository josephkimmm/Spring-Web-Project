package com.lighting.mypage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.lighting.util.DBUtil;

public class MeetingDAO {

    private Connection conn;
    private PreparedStatement pstat;
    private ResultSet rs;

    public MeetingDAO() {
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
    
 // 1. 내가 참여한 모임 보기 (Joined)
    // tblParticipationRequest와 JOIN하여, 사용자가 참여한 모임 전체를 조회합니다.
    public ArrayList<MeetingDTO> getJoinedMeetings(int userSeq, String sort) {
        ArrayList<MeetingDTO> list = new ArrayList<>();
        try {
            // 정렬 기준 수정 (모임 게시글 기준)
            String orderBy = "ORDER BY startTime DESC"; // 기본: 최신순
            switch(sort) {
                case "koreanOrder": orderBy = "ORDER BY mp.title ASC"; break;
                case "moreCapOrder": orderBy = "ORDER BY mp.capacity DESC"; break;
                case "lessCapOrder": orderBy = "ORDER BY mp.capacity ASC"; break;
                case "oldestOrder": orderBy = "ORDER BY mp.startTime ASC"; break;
            }
            String sql = "SELECT mp.tblMeetingPostSeq, mp.title, mp.location, mp.capacity, " +
                         "TO_CHAR(mp.startTime, 'YYYY\"년 \"FMMM\"월 \"FMDD\"일\"') AS startTime, " +
                         "pr.approvalStatus, pr.tblParticipationRequestSeq AS requestSeq " +
                         "FROM tblParticipationRequest pr " +
                         "JOIN tblMeetingPost mp ON pr.tblMeetingPostSeq = mp.tblMeetingPostSeq " +
                         "WHERE pr.tblMemberSeq = ? " + orderBy;
            
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, userSeq);
            rs = pstat.executeQuery();
            while(rs.next()){
                MeetingDTO dto = new MeetingDTO();
                dto.setTblMeetingPostSeq(rs.getString("tblMeetingPostSeq"));
                dto.setTitle(rs.getString("title"));
                dto.setLocation(rs.getString("location"));
                dto.setCapacity(rs.getInt("capacity"));
                dto.setStartTime(rs.getString("startTime"));
                dto.setApprovalStatus(rs.getString("approvalStatus"));
                dto.setRequestSeq(rs.getInt("requestSeq"));
                list.add(dto);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    
    // 2. 내가 작성한 글 보기 (written)
    // (여기서는 게시일(postDate) 기준 정렬 – MeetingDTO의 startTime 필드에 게시일 문자열을 저장)
    public ArrayList<MeetingDTO> getWrittenMeetings(int userSeq, String sort) {
        ArrayList<MeetingDTO> list = new ArrayList<>();
        try {
            String orderBy = "ORDER BY postDate DESC"; // 기본: 최신 게시
            switch(sort) {
                case "koreanOrder": orderBy = "ORDER BY title ASC"; break;
                case "moreCapOrder": orderBy = "ORDER BY capacity DESC"; break;
                case "lessCapOrder": orderBy = "ORDER BY capacity ASC"; break;
                case "oldestOrder": orderBy = "ORDER BY postDate ASC"; break;
            }
            String sql = "SELECT tblMeetingPostSeq, title, location, capacity, " +
                         "TO_CHAR(postDate, 'YYYY\"년 \"MM\"월 \"DD\"일\"') AS postDate " +
                         "FROM tblMeetingPost WHERE tblMemberSeq = ? " + orderBy;
            
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, userSeq);
            rs = pstat.executeQuery();
            while (rs.next()) {
                MeetingDTO dto = new MeetingDTO();
                dto.setTblMeetingPostSeq(rs.getString("tblMeetingPostSeq"));
                dto.setTitle(rs.getString("title"));
                dto.setLocation(rs.getString("location"));
                dto.setCapacity(rs.getInt("capacity"));
                // 게시일을 startTime 필드에 저장 (DTO에 별도 필드가 없다면)
                dto.setStartTime(rs.getString("postDate"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    
    // 3. 내가 찜한 모임 보기 (wish)
    public ArrayList<MeetingDTO> getWishMeetings(int userSeq, String sort) {
        ArrayList<MeetingDTO> list = new ArrayList<>();
        try {
            String orderBy = "ORDER BY m.startTime DESC"; // 기본: 최신순
            switch(sort) {
                case "koreanOrder": orderBy = "ORDER BY m.title ASC"; break;
                case "moreCapOrder": orderBy = "ORDER BY m.capacity DESC"; break;
                case "lessCapOrder": orderBy = "ORDER BY m.capacity ASC"; break;
                case "oldestOrder": orderBy = "ORDER BY m.startTime ASC"; break;
            }
            String sql = "SELECT m.tblMeetingPostSeq, m.title, m.location, m.capacity, " +
                         "TO_CHAR(m.startTime, 'YYYY\"년 \"FMMM\"월 \"FMDD\"일\"') AS startTime " +
                         "FROM tblMeetingPost m JOIN tblWishlist w " +
                         "ON m.tblMeetingPostSeq = w.tblMeetingPostSeq " +
                         "WHERE w.tblMemberSeq = ? " + orderBy;
            
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, userSeq);
            rs = pstat.executeQuery();
            while (rs.next()) {
                MeetingDTO dto = new MeetingDTO();
                dto.setTblMeetingPostSeq(rs.getString("tblMeetingPostSeq"));
                dto.setTitle(rs.getString("title"));
                dto.setLocation(rs.getString("location"));
                dto.setCapacity(rs.getInt("capacity"));
                dto.setStartTime(rs.getString("startTime"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    
    // 4. 내가 신청한 모임 보기 (requesting)
    public ArrayList<MeetingDTO> getRequestingMeetings(int userSeq, String sort) {
        ArrayList<MeetingDTO> list = new ArrayList<>();
        try {
            String orderBy = "ORDER BY m.startTime DESC"; // 기본 정렬
            switch(sort) {
                case "koreanOrder": orderBy = "ORDER BY m.title ASC"; break;
                case "moreCapOrder": orderBy = "ORDER BY m.capacity DESC"; break;
                case "lessCapOrder": orderBy = "ORDER BY m.capacity ASC"; break;
                case "oldestOrder": orderBy = "ORDER BY m.startTime ASC"; break;
            }
            
            String seq = userSeq + "";
            
            String sql = "SELECT m.tblMeetingPostSeq, m.title, m.location, m.capacity, " +
                         "TO_CHAR(m.startTime, 'YYYY\"년 \"FMMM\"월 \"FMDD\"일\"') AS startTime, " +
                         "pr.approvalStatus, pr.tblParticipationRequestSeq AS requestSeq " +
                         "FROM tblMeetingPost m " +
                         "JOIN tblParticipationRequest pr ON m.tblMeetingPostSeq = pr.tblMeetingPostSeq " +
                         "WHERE pr.tblMemberSeq = ? " + orderBy;
                Connection conn = DBUtil.open();
                PreparedStatement pstat = conn.prepareStatement(sql); 
                pstat.setString(1, seq);
                rs = pstat.executeQuery();
                
                while (rs.next()) {
                    MeetingDTO dto = new MeetingDTO();
                    dto.setTblMeetingPostSeq(rs.getString("tblMeetingPostSeq"));
                    dto.setTitle(rs.getString("title"));
                    dto.setLocation(rs.getString("location"));
                    dto.setCapacity(rs.getInt("capacity"));
                    dto.setStartTime(rs.getString("startTime"));
                    dto.setApprovalStatus(rs.getString("approvalStatus"));
                    dto.setRequestSeq(rs.getInt("requestSeq"));
                    list.add(dto);
                }
                return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 5. 내가 만든 모임 신청 보기 (requested)
    // 즉, 내가 작성한 모임 게시글 중에서 신청 받은 내역
    public ArrayList<MeetingDTO> getRequestedMeetings(int userSeq, String sort) {
        ArrayList<MeetingDTO> list = new ArrayList<>();
        try {
            // ** 수정됨: ORDER BY에서 m.startTime 대신 startTime(alias) 사용
            String orderBy = "ORDER BY startTime DESC"; // 기본: 최신순
            switch(sort) {
                case "koreanOrder": orderBy = "ORDER BY m.title ASC"; break;
                case "moreCapOrder": orderBy = "ORDER BY m.capacity DESC"; break;
                case "lessCapOrder": orderBy = "ORDER BY m.capacity ASC"; break;
                case "oldestOrder": orderBy = "ORDER BY startTime ASC"; break; // ** 수정됨: m.startTime -> startTime
            }
            String sql = "SELECT DISTINCT m.tblMeetingPostSeq, m.title, m.location, m.capacity, " +
                         "TO_CHAR(m.startTime, 'YYYY\"년 \"FMMM\"월 \"FMDD\"일\"') AS startTime " +
                         "FROM tblMeetingPost m JOIN tblParticipationRequest pr " +
                         "ON m.tblMeetingPostSeq = pr.tblMeetingPostSeq " +
                         "WHERE m.tblMemberSeq = ? " + orderBy;
            
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, userSeq);
            rs = pstat.executeQuery();
            while (rs.next()) {
                MeetingDTO dto = new MeetingDTO();
                dto.setTblMeetingPostSeq(rs.getString("tblMeetingPostSeq"));
                dto.setTitle(rs.getString("title"));
                dto.setLocation(rs.getString("location"));
                dto.setCapacity(rs.getInt("capacity"));
                dto.setStartTime(rs.getString("startTime"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    public int getMeetingSeqByHost(int hostSeq) {
        String sql = "SELECT m.tblMeetingSeq " +
                     "FROM tblMeeting m " +
                     "JOIN tblMeetingPost p ON m.tblMeetingPostSeq = p.tblMeetingPostSeq " +
                     "WHERE p.tblMemberSeq = ?";

        try (Connection conn = DBUtil.open(); PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setInt(1, hostSeq);
            ResultSet rs = stat.executeQuery();
            return rs.next() ? rs.getInt("tblMeetingSeq") : -1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int getLatestMeetingSeqByHost(int hostSeq) {
        String sql = "SELECT m.tblMeetingSeq FROM tblMeeting m " +
                     "JOIN tblMeetingPost p ON m.tblMeetingPostSeq = p.tblMeetingPostSeq " +
                     "WHERE p.tblMemberSeq = ? " +
                     "ORDER BY p.postDate DESC"; // 가장 최신 모임 하나만
        try (Connection conn = DBUtil.open(); PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setInt(1, hostSeq);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) return rs.getInt("tblMeetingSeq");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getLatestMeetingSeqByHostWithParticipants(int hostSeq) {
        String sql = "SELECT m.tblMeetingSeq " +
                     "FROM tblMeeting m " +
                     "JOIN tblMeetingPost p ON m.tblMeetingPostSeq = p.tblMeetingPostSeq " +
                     "WHERE p.tblMemberSeq = ? " +
                     "AND EXISTS ( " +
                     "    SELECT 1 FROM tblParticipationRequest r " +
                     "    WHERE r.tblMeetingPostSeq = p.tblMeetingPostSeq " +
                     "    AND r.approvalStatus = 'Y' " +  // ✅ 여기 고침
                     ") " +
                     "ORDER BY m.tblMeetingSeq DESC FETCH FIRST 1 ROWS ONLY";

        try (Connection conn = DBUtil.open();
             PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setInt(1, hostSeq);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getInt("tblMeetingSeq");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    
    // 특정 모임 게시글 기준으로 참석 승인되었지만 아직 평가되지 않은 회원 조회
    public List<MemberDTO> getApprovedParticipantsNotYetEvaluatedByPostSeq(int meetingPostSeq, int evaluatorSeq) {
        List<MemberDTO> list = new ArrayList<>();

        String sql = """
	        		SELECT m.*
						FROM tblParticipationRequest r
						JOIN tblMember m ON r.tblMemberSeq = m.tblMemberSeq
						WHERE r.approvalStatus = 'Y'
						  AND r.tblMeetingPostSeq = ?
						  AND m.tblMemberSeq != ?
						  AND m.tblMemberSeq NOT IN (
						      SELECT evaluatedMemberSeq
						      FROM tblEvaluation
						      WHERE tblMeetingSeq = (
						          SELECT tblMeetingSeq FROM tblMeeting WHERE tblMeetingPostSeq = ?
						      )
						      AND evaluatorMemberSeq = ?
						  )
        		""";

        try (Connection conn = DBUtil.open();
             PreparedStatement stat = conn.prepareStatement(sql)) {

        	stat.setInt(1, meetingPostSeq);  // r.tblMeetingPostSeq = ?
        	stat.setInt(2, evaluatorSeq);    // m.tblMemberSeq != ?
        	stat.setInt(3, meetingPostSeq);  // 서브쿼리
        	stat.setInt(4, evaluatorSeq);    // evaluator

            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                MemberDTO dto = new MemberDTO();
                dto.setId(rs.getString("id"));
                dto.setNickname(rs.getString("nickname"));
                dto.setPhotoFileName(rs.getString("photoFileName"));
                dto.setTblMemberSeq(rs.getInt("tblMemberSeq"));
                list.add(dto);
                
                System.out.println(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    
    //신청자 조회
    public List<ParticipationDTO> getApplicantsByPostSeq(int meetingPostSeq) {
        List<ParticipationDTO> list = new ArrayList<>();
        String sql = "SELECT m.nickname, r.approvalStatus, rr.reason, r.tblParticipationRequestSeq " +
                "FROM tblParticipationRequest r " +
                "JOIN tblMember m ON r.tblMemberSeq = m.tblMemberSeq " +
                "LEFT JOIN tblRejectionReason rr ON r.tblParticipationRequestSeq = rr.tblParticipationRequestSeq " +
                "WHERE r.tblMeetingPostSeq = ?";


        try (Connection conn = DBUtil.open(); PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setInt(1, meetingPostSeq);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                ParticipationDTO dto = new ParticipationDTO();
                dto.setNickname(rs.getString("nickname"));
                dto.setApprovalStatus(rs.getString("approvalStatus"));
                dto.setRejectionReason(rs.getString("reason"));
                dto.setTblParticipationRequestSeq(rs.getInt("tblParticipationRequestSeq"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 참여신청 승인 여부 업데이트
    public void updateApprovalStatus(int requestSeq, String approvalStatus) {
        String sql = "UPDATE tblParticipationRequest SET approvalStatus = ? WHERE tblParticipationRequestSeq = ?";

        try (Connection conn = DBUtil.open();
             PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setString(1, approvalStatus);
            stat.setInt(2, requestSeq);
            //stat.executeUpdate();
            int result = stat.executeUpdate(); // ← 결과 확인!
            System.out.println(">> 승인 상태 업데이트 결과: " + result + "건");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 거절사유 INSERT or UPDATE
    public void insertOrUpdateRejectionReason(int requestSeq, String reason) {
        String sql = "MERGE INTO tblRejectionReason r USING dual ON (r.tblParticipationRequestSeq = ?) " +
                     "WHEN MATCHED THEN UPDATE SET reason = ? " +
                     "WHEN NOT MATCHED THEN INSERT (tblRejectionReasonSeq, tblParticipationRequestSeq, reason) " +
                     "VALUES (seqRejectionReason.NEXTVAL, ?, ?)";

        try (Connection conn = DBUtil.open();
             PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setInt(1, requestSeq);
            stat.setString(2, reason);
            stat.setInt(3, requestSeq);
            stat.setString(4, reason);
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getMeetingPostSeqByMeetingSeq(int meetingSeq) {
        String sql = "SELECT tblMeetingPostSeq FROM tblMeeting WHERE tblMeetingSeq = ?";
        try (Connection conn = DBUtil.open();
             PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setInt(1, meetingSeq);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getInt("tblMeetingPostSeq");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


}
