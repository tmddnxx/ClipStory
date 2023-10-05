package com.example.movie.model.dto;

import lombok.*;

@Getter
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
   private int movieNo;
   private String movieName;
   private String director;
   private String actor;
   private String releaseDate;
   private int score;
   private String region;
   private String genre;
   private int audience;
   private int ranking;
   private String runningtime;
   private String outline;
   private String poster;
   private String mo;
}
