package com.lighting.photo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PhotoDAO {

    private Connection conn;
    private PreparedStatement pstat;

    public PhotoDAO() {
        try {
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) env.lookup("jdbc/pool");

            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 자원 해제 메서드
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

    public int addPhoto(PhotoDTO dto) {
        int result = 0; // 결과 변수 초기화

        String sql = "INSERT INTO tblAttachedPhoto (tblAttachedPhotoSeq, photoFileName) VALUES (seqAttachedPhoto.NEXTVAL, ?);";

        try {
            pstat = conn.prepareStatement(sql);
            // 첫 번째 인자 설정
            pstat.setString(1, dto.getPhotofilename()); // 사진 파일 이름만 설정

            result = pstat.executeUpdate(); // 쿼리 실행 결과 저장
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // PreparedStatement 자원 해제
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
}