package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDTO {
    private String superId; // 슈퍼계정 id
    private String superPw; // 슈퍼계정 pw
    private String superName; // 슈퍼계정 이름

}
