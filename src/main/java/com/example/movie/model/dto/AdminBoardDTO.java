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
    private int cno;
    private String title;
    private String content;
    private Date addDate;
    private String superId;
    private String superName;

}
