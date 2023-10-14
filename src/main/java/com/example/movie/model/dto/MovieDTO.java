package com.example.movie.model.dto;

import lombok.*;

@Getter
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
   private int movieNo; // 영화 PK 번호
   private String movieName; // 영화 이름
   private String director; // 감독이름
   private String actor; // 배우이름
   private String releaseDate; // 개봉일
   private int score; // 평점
   private String region; // 제작 지역
   private String genre; // 장르
   private int audience; // 관객수
   private int ranking; // 박스오피스 순위
   private String runningtime; // 러닝타임
   private String outline; // 개요
   private String poster; // 영화 포스터
   private String mo; // movie OR ott
   private float avgScore; // 평점
}
