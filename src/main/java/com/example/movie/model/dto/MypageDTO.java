package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MypageDTO {
    private String memberId;
    private String name;
    private String nickName;
    private String zzim;
    private String joinDate;
    private int contentNo;
    private String title;
    private int commentNo;
    private String comment;
    private String content;
    private String addDate;
    private int hit;


}
