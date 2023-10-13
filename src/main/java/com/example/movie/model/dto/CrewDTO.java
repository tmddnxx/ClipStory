package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrewDTO {
    private int crewNo; // 배우/감독 번호
    private String crewName; // 배우/감독 이름
    private String crewImg; // 배우/감독 사진
}
