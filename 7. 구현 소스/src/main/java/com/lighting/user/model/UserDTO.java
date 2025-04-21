package com.lighting.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {


	private String tblMemberSeq;
	private String id;
	private String pw;
    private String name;
    private String nickname;
    private String birthday;
    private String tel;
    private String email;
    private String gender;
    private String sido;
    private String gugun;
	    
}
