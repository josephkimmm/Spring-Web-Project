package com.lighting.meeting.model;

import lombok.Data;

@Data
public class BlockListDTO {
    
    private String blockerMemberSeq;
    private String blockedMemberSeq;
    private String blockDate;
    
}
