package com.GrooveSpring.conversation;

import com.GrooveSpring.conversation.dto.ConversationRequestDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    /**
     * Liste de toutes les conversations
     * @return
     */
    public List<Conversation> getConversation() {
        return conversationRepository.findAll();
    }

    /**
     * Liste des conversations pour un user
     * @param id
     * @return
     */
    public List<Conversation> getAllByUserId(Long id) {
        return conversationRepository.findByUser1Id(id);
    }

    /**
     * Liste des conversations d'un user groupés par user2
     * @param id
     * @return
     */
    public List<Conversation> getAllByUserIdGroupByUser2(Long id) {
        return conversationRepository.findConversationGroupByUser2(id);
    }

    /**
     * Recupere tous les messages correspondants à l'user1 et l'user2
     * 1re requete sur le user1
     * 2ème requete sur le user2
     * @param id1 user1_id
     * @param id2 user2_id
     * @return liste des conversations par utilisateur avec un autre utilisateur
     */
    public List<Conversation> getAllConversByid1AndId2(Long id1, Long id2) {
        // cree deux listes pour les ajouter ensemble
        return conversationRepository.findByUser1IdAndUser2Id(id1, id2);
    }

    /**
     * creation d'une conversation
     * !!!!!!!!!!!!!!!! pas utilisé car ne prends pas en compte l'existance des users !!!!!!!!!!!!!!!!
     * @param conversation
     * @return
     */
    public Conversation createConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    /**
     * creer une conversation en utilisant des utilisateurs existants
     * utilise par le controlleurSocket pour creer un message
     * @param conversationRequestDto modele minimum reçu du back : message + les id
     * @return
     */
    public Conversation createConversationWithUsers(ConversationRequestDto conversationRequestDto) {
        Long user1_id = conversationRequestDto.getUser1_id();
        Long user2_id = conversationRequestDto.getUser2_id();
        String laDate = Instant.now() +"";
        // conversationEntity.setDate(this.laDate);

        // Vérifie si les utilisateurs avec les IDs spécifiés existent
        User user1 = userRepository.findById(user1_id).orElse(null);
        User user2 = userRepository.findById(user2_id).orElse(null);

        // si user1 et 2 user2 pas null
        // on cree une nouvelle conversationEntity
        // sinon renvoi une erreur
        if(user1 != null  && user2 != null){
            Conversation conversation = new Conversation();
            conversation.setMessage(conversationRequestDto.getMessage());
            conversation.setDate(conversationRequestDto.getDate());
            conversation.setUser1(user1);
            conversation.setUser2(user2);
            return conversationRepository.save(conversation);
        } else {
            throw new IllegalArgumentException("L'un ou les deux utilisateurs n'existent pas.");
        }
    }

    /**
     * met à jour un message dans le modele
     * @param id
     * @param conversation
     * @return
     */
    public Conversation updateConversation(Long id, Conversation conversation) {
        return conversationRepository.findById(id)
                .map(message-> {
                    message.setMessage(conversation.getMessage());
                    message.setDate(conversation.getDate());
                    return conversationRepository.save(conversation);
                }).orElseThrow(()-> new RuntimeException("Conversation non trouvée !"));
    }

    /**
     * Supprime une conversation
     * @param id
     * @return
     */
    public void deleteConversation(Long id) {
        conversationRepository.deleteById(id);
    }

}
