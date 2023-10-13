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
    private int crewNo;
    private int movieNo;
    private String castRole;
    private String crewName; // 출연진이름
    private String crewImg; // 출연진이미지
}
