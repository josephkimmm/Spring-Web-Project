    package com.lighting.mypage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingDTO {

    private String tblMeetingPostSeq; //모임 번호
    private String title;      // 모임 제목
    private String location;   // 장소명
    private int capacity;      // 정원
    private String startTime;  // 모임 시작 시간
    private String approvalStatus;
    private int requestSeq;
}
