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
    private String nickName; // 작성자 닉네임
    private String comment; // 댓글 내용
    private String addDate; // 작성 날짜
    private String memberId; // 작성자 아이디
    private int contentNo; // 댓글이 작성된 게시글 번호
    private boolean isLogin; // 댓글 작성자 본인인지 판단
    private int parentNo; // 부모댓글 번호
}
