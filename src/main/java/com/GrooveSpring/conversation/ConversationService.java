package com.GrooveSpring.conversation;

import com.GrooveSpring.Musicien.Musicien;
import com.GrooveSpring.Musicien.MusicienRepository;
import com.GrooveSpring.conversation.dto.ConversationRequestDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final MusicienRepository musicienRepository;

    public ConversationService(ConversationRepository conversationRepository, MusicienRepository musicienRepository) {
        this.conversationRepository = conversationRepository;
        this.musicienRepository = musicienRepository;
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
    public List<Conversation> getAllByMusicienId(Long id) {
        return conversationRepository.findByMusicien1Id(id);
    }

    /**
     * Liste des conversations d'un user groupés par user2
     * @param id
     * @return
     */
    public List<Conversation> getAllByUserIdGroupByMusicien2(Long id) {
        return conversationRepository.findConversationGroupByMusicien2(id);
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
     * cree une conversation en utilisant des utilisateurs existants
     * utilise par le controlleurSocket pour creer un message
     * @param conversationRequestDto modele minimum reçu du back : message + les id
     * @return
     */
    public Conversation createConversationWithMusiciens(ConversationRequestDto conversationRequestDto) {
        Long user1_id = conversationRequestDto.getMusicien1_id();
        Long user2_id = conversationRequestDto.getMusicien1_id();
        String laDate = Instant.now() +"";
        // conversationEntity.setDate(this.laDate);

        // Vérifie si les utilisateurs avec les IDs spécifiés existent
        Musicien musicien1 = musicienRepository.findById(user1_id).orElse(null);
        Musicien musicien2 = musicienRepository.findById(user2_id).orElse(null);

        // si user1 et 2 user2 pas null
        // on cree une nouvelle conversationEntity
        // sinon renvoi une erreur
        if(musicien1 != null  && musicien2 != null){
            Conversation conversation = new Conversation();
            conversation.setMessage(conversationRequestDto.getMessage());
            conversation.setDate(conversationRequestDto.getDate());
            conversation.setMusicien1(musicien1);
            conversation.setMusicien2(musicien2);
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
