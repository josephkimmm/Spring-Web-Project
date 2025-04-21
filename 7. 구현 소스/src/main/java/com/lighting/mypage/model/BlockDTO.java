package com.lighting.mypage.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlockDTO {
    private int blockedMemberSeq;
    private String nickname;
}