package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO { // 배우
    private int actorNo; // 배우 임의넘버
    private String actorName; // 배우이름
    private String photo; // 배우사진
    private String movieName; // 영화이름
}
