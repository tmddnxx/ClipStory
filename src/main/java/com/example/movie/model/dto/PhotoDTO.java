package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDTO {
    private int photoNo; // 포토 번호
    private String photoImg; // 포토 경로
    private int movieNo; // 포토가 있는 영화 번호
}
