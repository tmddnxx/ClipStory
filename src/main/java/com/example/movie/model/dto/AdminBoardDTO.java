package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminBoardDTO {
    private int cno; // 공지사항 글번호
    private String title; // 공지사항 글제목
    private String content; // 공지사항 글내용
    private Date addDate; // 공지사항 작성날짜
    private String superId; // 작성한 관리자 아이디
    private String superName; // 작성한 관리자 이름

}
