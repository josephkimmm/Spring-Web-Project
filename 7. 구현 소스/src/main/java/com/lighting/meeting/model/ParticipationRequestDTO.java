package com.lighting.meeting.model;

import lombok.Data;

@Data
public class ParticipationRequestDTO {

    private String tblParticipationRequestSeq;
    private String tblMeetingPostSeq;
    private String tblMemberSeq;
    private String approvalStatus;

}
