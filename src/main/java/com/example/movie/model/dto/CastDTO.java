package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CastDTO {
    private int crewNo; // 배우/감독 번호
    private int movieNo; // 영화 번호
    private String castRole; // 역할
    private String crewName; // 출연진이름
    private String crewImg; // 출연진이미지
}
