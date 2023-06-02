package com.GrooveSpring.conversation;

import com.GrooveSpring.conversation.dto.ConversationRequestDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201", "http://localhost:4202", "http://localhost:4203"})
public class ConversationWebSocketController {
    private final ConversationService conversationService;
    private SimpMessagingTemplate messagingTemplate;

    public ConversationWebSocketController(ConversationService conversationService, SimpMessagingTemplate messagingTemplate) {
        this.conversationService = conversationService;
        this.messagingTemplate = messagingTemplate;
    }
    /**
     * recupere le message
     * l'enregistre en bdd
     * et renvoi à tous les utilisateurs
     * @param conversationRequestDto
     * @return
     */
    @MessageMapping("/send-message") // Mapping du point de terminaison pour recevoir les messages du client
    @SendTo("/topic/messages") // Envoi des messages aux abonnés sur "/topic/messages"
    public Conversation handleMessage(ConversationRequestDto conversationRequestDto) {
        // Traitez le message reçu et renvoyez une réponse
        System.out.println("Creation par hanleMessage");
        return conversationService.createConversationWithMusiciens(conversationRequestDto);
    }

    /**
     * Recupere en socket le message d'un utilisateur
     * cree une conversation après vérification de l'existence des id
     * en cours : ne doit renvoyer le message qu'a l'utilisateur concerné
     * on passe par @SendTo pour choisir l'id
     * @param conversationRequestDto
     * @return
     */
    @MessageMapping("/private-message")
    public Conversation receivePrivateMessage(ConversationRequestDto conversationRequestDto) {
        // Traitez le message reçu et renvoyez une réponse
        System.out.println("Creation par receivePrivateMessage, id : ");
        // Envoyer la réponse au topic dynamique
        Long id = conversationRequestDto.getMusicien1_id();
        Long idMusicien = conversationRequestDto.getMusicien2_id();
        System.out.println("getMusicien1_id " + id);
        System.out.println("getMusicien2_id " + idMusicien);
        messagingTemplate.convertAndSend("/topic/private/" + id, conversationRequestDto);
        messagingTemplate.convertAndSend("/topic/private/" + idMusicien, conversationRequestDto);
        return conversationService.createConversationWithMusiciens(conversationRequestDto);
    }

    /**
     * url qui recupere le message instantané suivant que l'utilisateur vient de poster
     * @param idMusicien
     * @return
     */
    @SendTo("/topic/private/{idMusicien}")
    public String getPrivateTopicUser(@DestinationVariable String idMusicien) {
        return idMusicien;
    }

    /**
     * url qui recupere le message instantané suivant l'utilisateur qui reçoit le message
     * @param id
     * @return
     */
    @SendTo("/topic/private/{id}")
    public String getPrivateTopicReceiver(@DestinationVariable String id) {
        return id;
    }

}
