package com.example.movie.model.dto;

import lombok.*;

@Getter
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private int reviewNo;
    private String nickName;
    private String addDate;
    private String review;
    private int score;
    private String movieName;
    private String memberId;
}
