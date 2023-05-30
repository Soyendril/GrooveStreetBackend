package com.GrooveSpring.conversation.dto;

import lombok.Data;
/**
 * permet de recuperer le format d'envoi du back
 * pour creer une conversation avec le bon id utilisateur et receveur
 */

@Data
public class ConversationRequestDto {
    private Long musicien1_id;
    private Long musicien2_id;
    private String message;
    private String date;
}
