package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO { // 게시판
    private int contentNo; // 게시물 번호
    private String title; // 제목
    private String content; // 내용
    private String nickName; // 닉네임
    private Timestamp addDate; // 작성일
    private int hit; // 조회수
    private String memberId; // 회원 아이디
    private int cnt; // 댓글 수
}
