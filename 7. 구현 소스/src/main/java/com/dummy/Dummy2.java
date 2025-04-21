package com.dummy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Dummy2 {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클 접속 URL (본인 환경에 맞게 수정)
    private static final String ID = "server"; // 오라클 접속 ID (본인 환경에 맞게 수정)
    private static final String PW = "java1234"; // 오라클 접속 PW (본인 환경에 맞게 수정)
    
    public static void main(String[] args) {

        // Dummy2.java

        //tblActivityRegion();
        //m2();
        try {
            //insertTblFriendRequest();
            //insertTblFriendList();
            //insertTblPhotoPost();
            //insertTblAttachedPhoto();
            //insertTblMeetingPost();
            insertTblLocationCoordinate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    
    private static void insertTblLocationCoordinate() throws IOException, SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            // 1. JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(URL, ID, PW);
            conn.setAutoCommit(false);

            writer = new BufferedWriter(new FileWriter("output2.txt", true)); // 파일 생성 및 BufferedWriter 연결 (append 모드)

            // 장소 정보 배열 (수정됨)
            String[][] locations = {
                    {"강남역 근처", "37.498008", "127.028024"},
                    {"역삼역 근처", "37.500404", "127.035669"},
                    {"신논현역 근처", "37.504468", "127.024859"}
            };

            // tblMeetingPost 테이블에서 location 가져오기
            String selectSql = "SELECT tblMeetingPostSeq, location FROM tblMeetingPost WHERE tblMeetingPostSeq = ?";
            pstmt = conn.prepareStatement(selectSql);

            // tblLocationCoordinate 더미 데이터 생성
            for (int meetingPostSeq = 1; meetingPostSeq <= 500; meetingPostSeq++) {
                pstmt.setInt(1, meetingPostSeq);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    String location = rs.getString("location");
                    String latitude = "";
                    String longitude = "";

                    // location에 따라 latitude, longitude 설정 (수정됨)
                    for (String[] loc : locations) {
                        if (loc[0].equals(location)) {
                            latitude = loc[1];
                            longitude = loc[2];
                            break;
                        }
                    }

                    // INSERT 문 생성
                    String sql = String.format("INSERT INTO tblLocationCoordinate (tblMeetingPostSeq, latitude, longitude) " +
                            "VALUES (%d, %s, %s);", meetingPostSeq, latitude, longitude);
                    writer.write(sql);
                    writer.newLine();
                }
            }
            conn.commit();

            System.out.println("output2.txt 파일에 tblLocationCoordinate 더미 데이터 추가 완료");

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // 트랜잭션 롤백
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // 5. 자원 해제
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }




    private static void insertTblMeetingPost() throws IOException, SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            // 1. JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(URL, ID, PW);
            conn.setAutoCommit(false);

            writer = new BufferedWriter(new FileWriter("output2.txt", true)); // 파일 생성 및 BufferedWriter 연결 (append 모드)

            Random random = new Random();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 내용 생성 (lorem ipsum)
            String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

            // tblCategorySub 테이블에서 categoryName 가져오기
            String[] categorySubNames = new String[51];
            String selectSubSql = "SELECT categoryName FROM tblCategorySub WHERE tblCategorySubSeq = ?";
            pstmt = conn.prepareStatement(selectSubSql);
            for (int i = 1; i <= 50; i++) {
                pstmt.setInt(1, i);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    categorySubNames[i] = rs.getString("categoryName");
                }
            }

            // tblCategoryMain 테이블에서 categoryName 가져오기
            String[] categoryMainNames = new String[7];
            String selectMainSql = "SELECT categoryName FROM tblCategoryMain WHERE tblCategoryMainSeq = ?";
            pstmt = conn.prepareStatement(selectMainSql);
            for (int i = 1; i <= 6; i++) {
                pstmt.setInt(1, i);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    categoryMainNames[i] = rs.getString("categoryName");
                }
            }

            // tblMeetingPost 더미 데이터 생성
            int postCountPerCategory = 10; // tblCategorySubSeq 당 생성할 게시글 수
            int cnt = 1;
            for (int categorySubSeq = 1; categorySubSeq <= 50; categorySubSeq++) {
                for (int i = 0; i < postCountPerCategory; i++) {
                    // 제목 생성 (tblCategorySub.categoryName 포함)
                    String categorySubName = categorySubNames[categorySubSeq];
                    String title = String.format("%s 모임", categorySubName);

                    // 게시일 생성 (현재 시점 ~ 3일 전)
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DATE, -random.nextInt(4)); // 0 ~ 3일 전
                    Date postDate = calendar.getTime();
                    String postDateStr = dateFormat.format(postDate);

                    // 장소 생성
                    String[] locations = {"역삼역 근처", "강남역 근처", "신논현역 근처"};
                    String location = locations[random.nextInt(locations.length)];

                    // 정원 생성 (3 ~ 6)
                    int capacity = random.nextInt(4) + 3; // 3 ~ 6

                    // 시작 시간 생성 (9시간 이후 ~ 33시간 이후)
                    calendar = Calendar.getInstance();
                    calendar.add(Calendar.HOUR_OF_DAY, random.nextInt(25) + 9); // 9 ~ 33시간 이후
                    Date startTime = calendar.getTime();
                    String startTimeStr = dateFormat.format(startTime);

                    // 종료 시간 생성 (시작 시간으로부터 2시간 이후)
                    calendar.add(Calendar.HOUR_OF_DAY, 2);
                    Date endTime = calendar.getTime();
                    String endTimeStr = dateFormat.format(endTime);

                    // 사진 파일명 생성 (basic + tblCategoryMain.categoryName + tblCategorySub.categoryName)
                    int categoryMainSeq = 0;
                    if (categorySubSeq >= 1 && categorySubSeq <= 7) {
                        categoryMainSeq = 1;
                    } else if (categorySubSeq >= 8 && categorySubSeq <= 17) {
                        categoryMainSeq = 2;
                    } else if (categorySubSeq >= 18 && categorySubSeq <= 23) {
                        categoryMainSeq = 3;
                    } else if (categorySubSeq >= 24 && categorySubSeq <= 32) {
                        categoryMainSeq = 4;
                    } else if (categorySubSeq >= 33 && categorySubSeq <= 41) {
                        categoryMainSeq = 5;
                    } else if (categorySubSeq >= 42 && categorySubSeq <= 50) {
                        categoryMainSeq = 6;
                    }
                    String categoryMainName = categoryMainNames[categoryMainSeq];
                    String photoFileName = "basic" + categoryMainName + categorySubName + ".png";

                    // 회원 번호 랜덤 생성 (1 ~ 1000)
                    int memberSeq = random.nextInt(1000) + 1;

                    String sql = String.format(
                            "INSERT INTO tblMeetingPost (tblMeetingPostSeq, title, content, postDate, location, capacity, startTime, endTime, photoFileName, tblMemberSeq, tblCategorySubSeq) " +
                                    "VALUES (%d, '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD HH24:MI:SS'), '%s', %d, TO_DATE('%s', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('%s', 'YYYY-MM-DD HH24:MI:SS'), '%s', %d, %d);",
                            cnt, title, loremIpsum, postDateStr, location, capacity, startTimeStr, endTimeStr, photoFileName, memberSeq, categorySubSeq
                    );
                    writer.write(sql);
                    writer.newLine();
                    cnt++;
                }
            }
            conn.commit();

            System.out.println("output2.txt 파일에 tblMeetingPost 더미 데이터 추가 완료");

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // 트랜잭션 롤백
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // 5. 자원 해제
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }




    private static void insertTblAttachedPhoto() throws IOException, SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            // 1. JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(URL, ID, PW);
            conn.setAutoCommit(false);

            writer = new BufferedWriter(new FileWriter("output2.txt", true)); // 파일 생성 및 BufferedWriter 연결 (append 모드)

            // tblAttachedPhoto 더미 데이터 생성
            int photoPostCount = 500; // tblPhotoPostSeq 범위: 1 ~ 500
            String photoFileName = "basicProfile.png"; // 사진 파일명

            for (int i = 1; i <= photoPostCount; i++) {
                String sql = String.format("INSERT INTO tblAttachedPhoto (tblAttachedPhotoSeq, tblPhotoPostSeq, photoFileName) " +
                        "VALUES (%d, %d, '%s');", i, i, photoFileName);
                writer.write(sql);
                writer.newLine();
            }
            conn.commit();

            System.out.println("output2.txt 파일에 tblAttachedPhoto 더미 데이터 추가 완료");

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // 트랜잭션 롤백
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // 5. 자원 해제
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void insertTblPhotoPost() throws IOException, SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            // 1. JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(URL, ID, PW);

            writer = new BufferedWriter(new FileWriter("output2.txt", true)); // 파일 생성 및 BufferedWriter 연결 (append 모드)

            Random random = new Random();

            // 제목 생성에 사용할 단어 배열
            String[] titlePrefixes = {"아름다운", "멋진", "즐거운", "행복한", "신나는", "기분좋은", "상쾌한", "따뜻한", "시원한", "화려한", "고요한", "평화로운", "역동적인", "활기찬", "매력적인"};
            String[] titleNouns = {"풍경", "일상", "여행", "추억", "순간", "기록", "사진", "하늘", "바다", "산", "도시", "거리", "골목", "카페", "음식"};

            // 내용 생성 (lorem ipsum)
            String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

            // tblPhotoPost 더미 데이터 생성
            int postCount = 500; // 생성할 게시글 수
            int memberCount = 1000; // 회원 수 범위: 1 ~ 1000

            for (int i = 0; i < postCount; i++) {
                // 제목 생성
                String titlePrefix = titlePrefixes[random.nextInt(titlePrefixes.length)];
                String titleNoun = titleNouns[random.nextInt(titleNouns.length)];
                String title = titlePrefix + " " + titleNoun;

                // 회원 번호 랜덤 생성
                int memberSeq = random.nextInt(memberCount) + 1; // 1 ~ 1000

                String sql = String.format("INSERT INTO tblPhotoPost (tblPhotoPostSeq, title, content, tblMemberSeq) " +
                        "VALUES (%d, '%s', '%s', %d);", i+1, title, loremIpsum, memberSeq);
                writer.write(sql);
                writer.newLine();
            }

            System.out.println("output2.txt 파일에 tblPhotoPost 더미 데이터 추가 완료");

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // 트랜잭션 롤백
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // 5. 자원 해제
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void insertTblFriendList() throws SQLException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            // 1. JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(URL, ID, PW);

            writer = new BufferedWriter(new FileWriter("output2.txt", true)); // 파일 생성 및 BufferedWriter 연결 (append 모드)

            // tblFriendList 더미 데이터 생성 (tblFriendRequest 기반)
            String selectSql = "SELECT requestingMemberSeq, requestedMemberSeq FROM tblFriendRequest WHERE approvalStatus = 'Y'";
            pstmt = conn.prepareStatement(selectSql);
            rs = pstmt.executeQuery();

            Set<String> usedFriendships = new HashSet<>(); // 중복 방지를 위한 친구 관계 저장 Set
            
            while (rs.next()) {
                System.out.println(11);
                int mainMemberSeq = rs.getInt("requestingMemberSeq");
                int subMemberSeq = rs.getInt("requestedMemberSeq");
                
                String friendship1 = mainMemberSeq + "," + subMemberSeq;
                String friendship2 = subMemberSeq + "," + mainMemberSeq;

                if (!usedFriendships.contains(friendship1) && !usedFriendships.contains(friendship2)) {
                    usedFriendships.add(friendship1);
                    usedFriendships.add(friendship2);

                    String sql1 = String.format("INSERT INTO tblFriendList (mainMemberSeq, subMemberSeq, addDate) VALUES (%d, %d, SYSDATE);", mainMemberSeq, subMemberSeq);
                    String sql2 = String.format("INSERT INTO tblFriendList (mainMemberSeq, subMemberSeq, addDate) VALUES (%d, %d, SYSDATE);", subMemberSeq, mainMemberSeq);
                    writer.write(sql1);
                    writer.newLine();
                    writer.write(sql2);
                    writer.newLine();
                }
            }

            System.out.println("output2.txt 파일에 tblFriendList 더미 데이터 추가 완료");

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // 트랜잭션 롤백
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // 5. 자원 해제
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertTblFriendRequest() throws IOException {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("output2.txt", true)); // 파일 생성 및 BufferedWriter 연결 (append 모드)

            Random random = new Random();
            Set<String> usedRequests = new HashSet<>(); // 중복 방지를 위한 친구 요청 저장 Set

            // tblFriendRequest 더미 데이터 생성
            int requestCount = 200; // 생성할 친구 요청 수
            int memberCount = 1000; // 회원 수 범위: 1 ~ 1000

            for (int i = 0; i < requestCount; i++) {
                int requestingMemberSeq;
                int requestedMemberSeq;
                do {
                    requestingMemberSeq = random.nextInt(memberCount) + 1; // 1 ~ 1000
                    requestedMemberSeq = random.nextInt(memberCount) + 1; // 1 ~ 1000
                } while (requestingMemberSeq == requestedMemberSeq || usedRequests.contains(requestingMemberSeq + "," + requestedMemberSeq));

                usedRequests.add(requestingMemberSeq + "," + requestedMemberSeq);

                String approvalStatus = random.nextBoolean() ? "Y" : "N"; // 승인 여부 (Y or N)

                String sql = String.format("INSERT INTO tblFriendRequest (tblFriendRequestseq, requestingMemberSeq, requestedMemberSeq, approvalStatus) " +
                        "VALUES (seqFriendRequest.nextVal, %d, %d, '%s');", requestingMemberSeq, requestedMemberSeq, approvalStatus);
                writer.write(sql);
                writer.newLine();
            }

            System.out.println("output2.txt 파일에 tblFriendRequest 더미 데이터 추가 완료");

        } finally {
            // 자원 해제
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static void m2() {
     // 랜덤 생성기 및 데이터 저장을 위한 세트
        Random random = new Random();
        HashSet<String> relationships = new HashSet<>();

        int totalRelations = 200; // 총 친구 관계 수
        int memberCount = 1000; // 회원 수 범위: 1 ~ 1000

        while (relationships.size() < totalRelations * 2) { // 쌍방 관계 포함
            int mainMemberSeq = random.nextInt(memberCount) + 1; // 1 ~ 1000
            int subMemberSeq = random.nextInt(memberCount) + 1; // 1 ~ 1000

            // 같은 회원끼리 친구 관계는 제외
            if (mainMemberSeq != subMemberSeq) {
                String relation1 = mainMemberSeq + "," + subMemberSeq;
                String relation2 = subMemberSeq + "," + mainMemberSeq;

                // 중복 관계 방지
                if (!relationships.contains(relation1) && !relationships.contains(relation2)) {
                    relationships.add(relation1);
                    relationships.add(relation2);

                    // INSERT 문 출력
                    System.out.println("INSERT INTO tblFriendList (mainMemberSeq, subMemberSeq, addDate) VALUES (" 
                                       + mainMemberSeq + ", " + subMemberSeq + ", SYSDATE);");
                    System.out.println("INSERT INTO tblFriendList (mainMemberSeq, subMemberSeq, addDate) VALUES (" 
                                       + subMemberSeq + ", " + mainMemberSeq + ", SYSDATE);");
                }
            }
        }


        
    }

    private static void tblActivityRegion() {
        BufferedWriter writer = null; // BufferedWriter 선언
        try {
            writer = new BufferedWriter(new FileWriter("output2.txt", true)); // 파일 생성 및 BufferedWriter 연결 (append 모드)

            Random random = new Random();

            // tblActivityRegion 더미 데이터 생성
            for (int i = 1; i <= 1000; i++) { // 1000명의 회원
                    int regionSeq = random.nextInt(25) + 1; // 1~25개의 활동 지역 좌표
                    String sql = String.format("INSERT INTO tblActivityRegion (tblMemberSeq, tblActivityRegionCoordinateSeq) VALUES (%d, %d);", i, regionSeq);
                    writer.write(sql);
                    writer.newLine();
            }
            System.out.println("output.txt 파일에 tblActivityRegion 더미 데이터 추가 완료");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close(); // 파일 닫기
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

















