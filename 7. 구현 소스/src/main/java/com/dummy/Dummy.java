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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Dummy {

    public static void main(String[] args) {

        // Dummy.java

        // 데이터베이스 연결 정보
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클 접속 URL (본인 환경에 맞게 수정)
        String id = "server"; // 오라클 접속 ID (본인 환경에 맞게 수정)
        String pw = "java1234"; // 오라클 접속 PW (본인 환경에 맞게 수정)

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 더미 데이터 생성
        Random random = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String[] firstNames = {"김", "이", "박", "최", "정", "강", "조", "윤", "장", "임"};
        String[] middleNames = {"민", "지", "현", "서", "예", "하", "도", "주", "승", "준"};
        String[] lastNames = {"수", "영", "아", "은", "진", "우", "연", "희", "호", "빈"};
        String[] genders = {"M", "F"};
        String[] domains = {"naver.com", "gmail.com", "daum.net", "hanmail.net"};

        Set<String> usedEmails = new HashSet<>(); // 중복 방지를 위한 이메일 저장 Set
        Set<String> usedTels = new HashSet<>(); // 중복 방지를 위한 전화번호 저장 Set
        Set<String> usedNicknames = new HashSet<>(); // 중복 방지를 위한 닉네임 저장 Set

        BufferedWriter writer = null; // BufferedWriter 선언
        try {
            // 1. JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(url, id, pw);
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            writer = new BufferedWriter(new FileWriter("output.txt")); // 파일 생성 및 BufferedWriter 연결

            // tblCategoryMain 더미 데이터 생성
            String[] categoryMain = {"스포츠", "게임", "맛집", "문화", "힐링", "자기계발"};
            for (int i = 0; i < categoryMain.length; i++) {
                String sql = String.format("INSERT INTO tblCategoryMain (tblCategoryMainSeq, categoryName) VALUES (%d, '%s');",i+1, categoryMain[i]);
                writer.write(sql);
                writer.newLine();
            }

         // tblCategorySub 더미 데이터 생성
            String[][] categorySub = {
                    {"유산소", "웨이트(무산소)", "격투", "코어&밸런스", "기능성", "다이어트", "기타"}, // 스포츠
                    {"액션", "어드밴처", "롤플레잉(RPG)", "시뮬레이션", "전략", "스포츠", "퍼즐", "아케이드", "슈팅", "기타"}, // 게임
                    {"식사", "카페", "디저트", "술", "비건", "기타"}, // 맛집
                    {"영화", "공연", "전시회&미술", "노래&춤", "언어교환", "애니메이션", "쇼핑", "악기연주", "기타"}, // 문화
                    {"스터디", "취업준비", "모의면접", "멘토&멘티", "독서", "재테크", "비즈니스", "코딩", "기타"}, // 힐링
                    {"여행", "드라이브", "캠핑", "등산", "명상", "반려동물", "꽃꽃이", "독서", "기타"} // 자기계발
            };
            int[] categoryMainSeq = {1, 2, 3, 4, 5, 6};
            int cnt = 1;
            for (int i = 0; i < categorySub.length; i++) {
                for (int j = 0; j < categorySub[i].length; j++) {
                    String sql = String.format("INSERT INTO tblCategorySub (tblCategorySubSeq, tblCategoryMainSeq, categoryName) VALUES (%d, %d, '%s');", cnt, categoryMainSeq[i], categorySub[i][j]);
                    cnt++;
                    writer.write(sql);
                    writer.newLine();
                }
            }

            // tblMember 더미 데이터 생성
            for (int i = 0; i < 1000; i++) { // 1000개의 더미 데이터 생성
                // 아이디 생성 (4~20자리, 영문+숫자 조합)
                String memberId = generateMemberId(random);

                // 비밀번호 생성 (영문+숫자+특수문자 조합)
                String memberPw = generateMemberPw(random);

                // 이름 생성 (랜덤 성 + 랜덤 이름)
                String firstName = firstNames[random.nextInt(firstNames.length)];
                String middleName = middleNames[random.nextInt(middleNames.length)];
                String lastName = lastNames[random.nextInt(lastNames.length)];
                String memberName = firstName + middleName + lastName;

                // 닉네임 생성 (의미있는 단어 조합, 중복 방지)
                String memberNickname;
                do {
                    memberNickname = generateMemberNickname(random);
                } while (usedNicknames.contains(memberNickname));
                usedNicknames.add(memberNickname);

                // 생년월일 생성 (1970 ~ 2000년 사이)
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, random.nextInt(31) + 1970); // 1970 ~ 2000
                calendar.set(Calendar.MONTH, random.nextInt(12)); // 0 ~ 11
                calendar.set(Calendar.DAY_OF_MONTH, random.nextInt(28) + 1); // 1 ~ 28
                Date memberBirthday = calendar.getTime();
                String memberBirthdayStr = dateFormat.format(memberBirthday);

                // 연락처 생성 (010-xxxx-xxxx, 중복 방지)
                String memberTel;
                do {
                    memberTel = "010-" + String.format("%04d", random.nextInt(10000)) + "-" + String.format("%04d", random.nextInt(10000));
                } while (usedTels.contains(memberTel));
                usedTels.add(memberTel);

                // 이메일 생성 (id + @ + 랜덤 도메인, 중복 방지)
                String memberEmail;
                do {
                    memberEmail = memberId + "@" + domains[random.nextInt(domains.length)];
                } while (usedEmails.contains(memberEmail));
                usedEmails.add(memberEmail);

                // 성별 생성 (M or F)
                String memberGender = genders[random.nextInt(genders.length)];

                // 가입일 생성 (현재 날짜)
                Date memberRegistrationDate = new Date();
                String memberRegistrationDateStr = dateFormat.format(memberRegistrationDate);

                // INSERT 문 생성
                String sql = String.format(
                        "INSERT INTO tblMember (tblMemberSeq, id, pw, name, nickname, birthday, tel, email, gender, photoFileName, registrationDate, status) " +
                                "VALUES (%d, '%s', '%s', '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'), '%s', '%s', '%s', 'basicProfile.png', default, 0);",
                        i+1, memberId, memberPw, memberName, memberNickname, memberBirthdayStr, memberTel, memberEmail, memberGender
                );

                // 파일에 INSERT 문 작성
                writer.write(sql);
                writer.newLine(); // 줄바꿈
            }


            for (int i=1; i<=1000; i++) {
                String sql = String.format("INSERT INTO tblLoginStatus (tblMemberSeq, status) VALUES (%d, 'N');", i);
                writer.write(sql);
                writer.newLine();
            }

            for (int i=1; i<=1000; i++) {
                String sql = String.format("INSERT INTO tblPasswordError (tblMemberSeq, count) VALUES (%d, 0);", i);
                writer.write(sql);
                writer.newLine();
            }

            System.out.println("output.txt 파일 생성 완료");

        } catch (Exception e) {
            e.printStackTrace();
            if(conn != null) {
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

    // 아이디 생성 (4~20자리, 영문+숫자 조합)
    private static String generateMemberId(Random random) {
        int length = random.nextInt(17) + 4; // 4 ~ 20
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (random.nextBoolean()) {
                sb.append((char) (random.nextInt(26) + 'a')); // 소문자
            } else {
                sb.append(random.nextInt(10)); // 숫자
            }
        }
        return sb.toString();
    }

    // 비밀번호 생성 (영문+숫자+특수문자 조합)
    private static String generateMemberPw(Random random) {
        String specialChars = "!@#$%^&*()";
        StringBuilder sb = new StringBuilder();

        //최소 1개 이상의 영문, 숫자, 특수문자 포함
        sb.append((char) (random.nextInt(26) + 'a'));
        sb.append(random.nextInt(10));
        sb.append(specialChars.charAt(random.nextInt(specialChars.length())));

        int length = random.nextInt(10) + 8; // 8 ~ 17
        for (int i = 3; i < length; i++) {
            int type = random.nextInt(3); // 0: 영문, 1: 숫자, 2: 특수문자
            if (type == 0) {
                sb.append((char) (random.nextInt(26) + 'a')); // 소문자
            } else if (type == 1) {
                sb.append(random.nextInt(10)); // 숫자
            } else {
                sb.append(specialChars.charAt(random.nextInt(specialChars.length()))); // 특수문자
            }
        }
        return sb.toString();
    }

    // 닉네임 생성 (의미있는 단어 조합)
    private static String generateMemberNickname(Random random) {
        String[] adjectives = {"멋진", "귀여운", "용감한", "친절한", "똑똑한", "행복한", "신나는", "즐거운", "상냥한", "다정한", "활발한", "조용한", "든든한", "유쾌한", "재미있는"};
        String[] nouns = {"사자", "호랑이", "강아지", "고양이", "토끼", "나무", "별", "달", "해", "바람", "구름", "꽃", "하늘", "바다", "산", "숲", "강", "돌", "모래", "햇살"};

        String adjective = adjectives[random.nextInt(adjectives.length)];
        String noun = nouns[random.nextInt(nouns.length)];
        int number = random.nextInt(100); // 0 ~ 99

        return String.format("%s%s%02d", adjective, noun, number);
    }
}
