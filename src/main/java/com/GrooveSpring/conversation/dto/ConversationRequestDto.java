package com.GrooveSpring.conversation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * permet de recuperer le format d'envoi du back
 * pour creer une conversation avec le bon id utilisateur et receveur
 */

@Data
public class ConversationRequestDto {
    private Long musicien1_id;
    private Long musicien2_id;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String photo;
}
