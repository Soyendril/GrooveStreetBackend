package com.GrooveSpring.conversation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data

public class ConversationParMusicienDto {
    private Long id;
    private String nom;
    private String photo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String message;
}
