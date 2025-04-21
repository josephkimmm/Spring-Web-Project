package com.lighting.meeting.model;

import lombok.Data;

@Data
public class FriendRequestDTO {
    
    private String tblFriendRequestseq;
    private String requestingMemberSeq;
    private String requestedMemberSeq;
    private String approvalStatus;
    
}
