package com.example.movie.model.dto;

import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String memberId;
    private String passwd;
    private String name;
    private String nickName;
    private String joinDate;
    private String zzim;
}
