package com.example.movie.model.dto;

import lombok.*;

@Getter
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private String superId; // 슈퍼계정 id
    private String superPw; // 슈퍼계정 pw
    private String superName; // 슈퍼계정 이름
}
