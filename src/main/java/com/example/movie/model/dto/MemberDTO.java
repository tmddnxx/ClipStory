package com.example.movie.model.dto;

import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO { // 회원정보
    private String memberId; // 아이디
    private String passwd; // 비밀번호
    private String name; // 이름
    private String nickName; // 닉네임
    private int zzimCnt; // 찜 개수
    private String joinDate; // 가입일
    private String zzim;
}
