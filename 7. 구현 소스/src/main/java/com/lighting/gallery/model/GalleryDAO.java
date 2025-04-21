package com.lighting.gallery.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class GalleryDAO {

	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;

	public GalleryDAO() {
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
			if (pstat != null) {
				pstat.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int addPhoto(GalleryDTO dto) {

		int result = 0; // 결과 변수 초기화
		String sql = "INSERT INTO tblPhotoPost (tblPhotoPostSeq, tblMemberSeq, photoFileName) VALUES (seqPhotoPost.NEXTVAL, ?, ?)";

		try {
			// SQL 쿼리 수정
			pstat = conn.prepareStatement(sql);
			System.out.println("tblMemberSeq: " + dto.getTblmemberseq());

			// pstat.setString(1, dto.getTblPhotoPostSeq()); // tblPhotoPostSeq 값 설정
			pstat.setString(1, dto.getTblmemberseq()); // 사진 파일 이름
			pstat.setString(2, dto.getPhotofilename()); // 사진 파일 이름
			

			// 쿼리 실행
			result = pstat.executeUpdate(); // 쿼리 실행 결과 저장

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstat != null) {
					pstat.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result; // 작업 결과 반환
	}

	public List<String> getAllPhotoFilenames() {

		List<String> photoFilenames = new ArrayList<>();

		String sql = "SELECT photoFileName FROM tblPhotopost";

		try {
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();

			while (rs.next()) {
				String filename = rs.getString("photoFileName");
				photoFilenames.add(filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(); // 리소스 정리
		}

		return photoFilenames;
	}

	public List<String> getAllPhotoFilenamesOrderByOldest() {
		List<String> photoFilenames = new ArrayList<>();
		String sql = "SELECT photoFileName FROM tblAttachedPhoto ORDER BY tblAttachedPhotoSeq ASC"; // 오래된 순

		try {
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();

			while (rs.next()) {
				String filename = rs.getString("photoFileName");
				photoFilenames.add(filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(); // 리소스 정리
		}

		return photoFilenames;
	}

	public List<String> getAllPhotoFilenamesOrderByLatest() {
		List<String> photoFilenames = new ArrayList<>();
		
		String sql = "SELECT photoFileName FROM tblAttachedPhoto ORDER BY tblAttachedPhotoSeq DESC"; // 최신순

		try {
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();

			while (rs.next()) {
				String filename = rs.getString("photoFileName");
				photoFilenames.add(filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(); // 리소스 정리
		}

		return photoFilenames;
	}

	public List<String> getMyPhotoFilenames(String tblMemberSeq) {

		List<String> photoFilenames = new ArrayList<>();

		String sql = "select * from tblPhotoPost";
		
		try {
			pstat = conn.prepareStatement(sql);
			// pstat.setString(1, tblMemberSeq);
			//pstat.setString(1, tblMemberSeq);
			ResultSet rs = pstat.executeQuery();

			while (rs.next()) {
				String filename = rs.getString("photoFileName");
				photoFilenames.add(filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(); // 리소스 정리
		}

		return photoFilenames;
	}

	public int deletePhotos(String tblMemberSeq) {
	    int result = 0;
	    String sql = "delete tblphotopost where tblphotopostseq = (select tblphotopostseq from tblphotopost where tblmemberseq= ?)";

	    try {
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, tblMemberSeq);
	        rs = pstat.executeQuery();
	        System.out.println(rs);		
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        close(); // 리소스 정리
	    }

	    return result; // 삭제된 행 수 반환
	}
}
