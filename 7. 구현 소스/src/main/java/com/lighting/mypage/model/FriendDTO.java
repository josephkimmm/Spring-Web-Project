package com.lighting.mypage.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FriendDTO {
    private int friendMemberSeq; // 친구의 회원 번호 (tblFriendList의 subMemberSeq)
    private String nickname;     // 친구의 닉네임 (tblMember의 값)


}
     
