package com.GrooveSpring.Musicien.dto;

import lombok.Data;

@Data
public class MusicienAuthDto {
    private Long id;
    private String nom;
    private String password;
    private String email;
    private String pseudo;
    private String photo;

}
