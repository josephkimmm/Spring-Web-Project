package com.lighting.meeting.model;

import lombok.Data;

@Data
public class MeetingPostDTO {
    
    private String tblMeetingPostSeq;
    private String title;
    private String content;
    private String postDate;
    private String location;
    private String capacity;
    private String startTime; //'YYYY-MM-DD HH24:MM'
    private String endTime;//FIXME
    private String photoFileName;//FIXME
    private String tblMemberSeq;
    private String tblCategorySubSeq;
    private String latitude;
    private String longitude;
}
