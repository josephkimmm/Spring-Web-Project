package com.lighting.mypage.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {

    private int tblMemberSeq;       // 회원 seq
    private String id;              // 아이디
    private String pw;              // 비밀번호
    private String nickname;        // 닉네임
    private String tel;             // 전화번호
    private String email;           // 이메일
    private String photoFileName;   // 사진 파일명
    private int status;             // 상태(활동중 = 0, 탈퇴,비공개=1)
    private double grade;
}