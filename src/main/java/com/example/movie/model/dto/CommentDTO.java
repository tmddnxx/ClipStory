package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int commentNo; // 댓글 번호
    private String nickName;
    private String comment;
    private String addDate;
    private String memberId;
    private int contentNo;
    private boolean isLogin;
    private int parentNo;
}
