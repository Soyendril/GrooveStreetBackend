package com.GrooveSpring.Musicien.dto;

import lombok.Data;

@Data
public class MusicienAuthDto {
    private Long id;
    private String email;
    private String password;
}
