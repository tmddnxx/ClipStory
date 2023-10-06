package com.example.movie.model.dto;

import lombok.*;

@Getter
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Integer reviewNo; // rippleId
    private Integer movieNo; // movieNo
    private String memberId; // memberId
    private String nickName; // name
    private String review; // content
    private String addDate; // insertDate
    private String score; // ip??
    private boolean isLogin; // isLogin

}
