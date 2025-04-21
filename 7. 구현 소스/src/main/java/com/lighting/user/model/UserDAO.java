package com.lighting.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;
    
    public UserDAO() {
        
        try {
            
            Context ctx = new InitialContext();
            Context env = (Context)ctx.lookup("java:comp/env");
            DataSource ds = (DataSource)env.lookup("jdbc/pool");
            
            conn = ds.getConnection();
            stat = conn.createStatement();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void close() {
        try {
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public UserDTO login(UserDTO dto) {
        try {
            
            String sql = "select * from tblmember where id = ? and pw = ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getId());
            pstat.setString(2, dto.getPw());
            
            rs = pstat.executeQuery();
            
            System.out.println(rs);
            
            if (rs.next()) {
                //로그인 성공
                UserDTO result = new UserDTO();
                
                result.setTblMemberSeq(rs.getString("tblMemberSeq"));
                System.out.println(result);
                
                return result;
                
            } else {
                //로그인 실패
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 아이디 중복 검사
    public boolean isDuplicateId(String id) {
        String query = "SELECT COUNT(*) FROM tblMember WHERE id = ?";
        System.out.println("Checking ID: " + id);  // ID 확인
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("Duplicate count: " + count);  // 중복된 ID의 개수
                    return count > 0; // 중복된 아이디가 있으면 true
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // 중복된 아이디가 없으면 false
    }

    // 회원 정보를 추가하는 메서드
    public int addMember(UserDTO memberDto) {
        String query = "INSERT INTO tblMember (tblMemberSeq, id, pw, name, nickname, birthday, tel, email, gender) VALUES (seqMember.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, memberDto.getId());
            stmt.setString(2, memberDto.getPw());
            stmt.setString(3, memberDto.getName());
            stmt.setString(4, memberDto.getNickname());
            stmt.setString(5, memberDto.getBirthday());
            stmt.setString(6, memberDto.getTel());
            stmt.setString(7, memberDto.getEmail());
            stmt.setString(8, memberDto.getGender());
 
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return 1; // 성공적으로 삽입되었을 경우 1 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 실패
    }  



    	
    
    

    public int addUser(UserDTO dto) {
        String query = "INSERT INTO tblMember (tblMemberSeq, id, pw, name, nickname, birthday, tel, email, gender, photofilename, registrationdate, status) " +
                       "VALUES (seqMember.nextval, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, 'basicProfile.png', default, 0)";
        try {
            pstat = conn.prepareStatement(query);
            pstat.setString(1, dto.getId());
            pstat.setString(2, dto.getPw());
            pstat.setString(3, dto.getName());
            pstat.setString(4, dto.getNickname());
            pstat.setString(5, dto.getBirthday());  // 여기서 이미 1982-10-01 형태로 변환됨
            pstat.setString(6, dto.getTel());
            pstat.setString(7, dto.getEmail());
            pstat.setString(8, dto.getGender());

            // 쿼리 실행 후 영향을 받은 행 수를 확인
            int rowsAffected = pstat.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);  // 실행된 행 수 출력

            if (rowsAffected > 0) {
                return 1;  // 정상적으로 데이터가 추가되면 1 반환
            } else {
                System.out.println("회원 추가 실패: 영향을 받은 행 없음");
            }
        } catch (SQLException e) {
            System.out.println("SQL 오류 발생: " + e.getMessage());  // SQL 오류 메시지 출력
            e.printStackTrace();
        }
        return -1;  // 실패 시 -1 반환
    }
		




	public int validEmail(HashMap<String, String> map) {
	    String query = "SELECT validnumber FROM tblEmail WHERE email = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, map.get("email"));
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                String storedValidNumber = rs.getString("validnumber");
	                System.out.println("Stored valid number: " + storedValidNumber); // 로그 추가
	                // 인증번호가 일치하면 인증 성공 (1 반환)
	                if (storedValidNumber.equals(map.get("validNumber"))) {
	                    return 1; // 인증 성공
	                }
	            }
	        }
	        
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0; // 인증 실패
	}



	public void delEmail(String email) {
	    String query = "DELETE FROM tblEmail WHERE email = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, email);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	

	public void addEmail(HashMap<String, String> map) {
	    String query = "INSERT INTO tblEmail (email, validnumber, regdate) VALUES (?, ?, sysdate)";
	    System.out.println("map: " + map);
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, map.get("email"));
	        stmt.setString(2, map.get("validNumber"));
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	      
	    }
	}


//마지막으로 삽입된 회원의 tblMemberSeq를 가져오는 메서드
	public int getLastInsertedMemberSeq() {
		String sql = "SELECT max(tblMemberSeq) FROM tblMember";
		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1); // 마지막으로 삽입된 tblMemberSeq 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 실패 시 -1 반환
	}




//시도와 구군으로 해당 활동지역의 좌표seq를 가져오는 메서드
	public int getActivityRegionCoordinateSeq(String sido, String gugun) {
		String sql = "SELECT tblActivityRegionCoordinateSeq FROM tblActivityRegionCoordinate WHERE sido = ? AND gugun = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, sido);
			stmt.setString(2, gugun);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("tblActivityRegionCoordinateSeq"); // 해당 좌표seq 반환
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 해당 좌표가 없으면 -1 반환
	}
	
	// 회원과 활동 지역을 tblActivityRegion 테이블에 연결하는 메서드
	public void addActivityRegion(int memberSeq, int activityRegionCoordinateSeq) {
	    String sql = "INSERT INTO tblActivityRegion (tblMemberSeq, tblActivityRegionCoordinateSeq) VALUES (?, ?)";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, memberSeq);
	        stmt.setInt(2, activityRegionCoordinateSeq);
	        stmt.executeUpdate(); // 데이터 삽입
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public String findUserIdByNameAndTel(String name, String tel) {
		
		 String sql = "SELECT id FROM tblMember WHERE name = ? AND tel = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, name);
	            stmt.setString(2, tel); // 'contact' 대신 'tel'을 사용
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getString("id"); // 아이디 반환
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return null;
	}
	public String findUserPasswordByNameAndIdTel(String name, String id, String tel) {
		
		 String sql = "SELECT pw FROM tblMember WHERE name = ? AND id = ? AND tel = ?";
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, name);
		        stmt.setString(2, id); // id 추가
		        stmt.setString(3, tel); // tel 추가
		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                return rs.getString("pw"); // 비밀번호 반환
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return null; // 일치하는 비밀번호가 없으면 null 반환
		}
	

    

}
