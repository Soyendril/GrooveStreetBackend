package com.GrooveSpring.conversation;

import com.GrooveSpring.Musicien.Musicien;
import com.GrooveSpring.Musicien.MusicienRepository;
import com.GrooveSpring.conversation.dto.ConversationDetailDto;
import com.GrooveSpring.conversation.dto.ConversationParMusicienDto;
import com.GrooveSpring.conversation.dto.ConversationRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;


@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final MusicienRepository musicienRepository;

    private final ObjectMapper mapper;

    public ConversationService(ConversationRepository conversationRepository, MusicienRepository musicienRepository, ObjectMapper mapper) {
        this.conversationRepository = conversationRepository;
        this.musicienRepository = musicienRepository;
        this.mapper = mapper;
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
     * Liste des conversations d'un musicien groupés par user2
     * permet de lister toutes les conversations privées d'un musicien
     * recupere une premiere liste de toutes les colones 2 où est présent l'id donné
     * recupere une deuxieme liste de toutes les colones 1 où est présent l'id donné
     * parcours la première liste et ajoute les éléments de la deuxième non identiques
     * @param idMusicien
     * @return ConversationParMusicienDto = conversation avec uniquement id et nom avec qui le musicien a discuté
     */
    public List<ConversationParMusicienDto> getAllByMusicienIdGroupByMusicien2(Long idMusicien) {
//         return conversationRepository.findConversationGroupByMusicien2(id);

        List<Object[]> conversations1 = conversationRepository.findConversationByMusicien1(idMusicien);
        List<ConversationParMusicienDto> conversationDtos1 = new ArrayList<>();
        for (Object[] conversation : conversations1) {
            Long idMusicien1 = (Long) conversation[0];
            String nom = (String) conversation[1];
            String photo = (String) conversation[2];
            Date date = (Date) conversation[3];
            String message = (String) conversation[4];
            // si idMusicien existe déja :
            // - verfie la date :
            //   - si plus récente : recupere la date + le message uniquement pour le modifier : pas de création
            if (!containsConversation(conversationDtos1, idMusicien1, nom)) {
                ConversationParMusicienDto conversationDto = new ConversationParMusicienDto();
                conversationDto.setId(idMusicien1);
                conversationDto.setNom(nom);
                conversationDto.setPhoto(photo);
                conversationDto.setDate(date);
                conversationDto.setMessage(message);
                // creation de la premiere requete
                conversationDtos1.add(conversationDto);
            }
        }

        List<Object[]> conversations2 = conversationRepository.findConversationByMusicien2(idMusicien);
        for (Object[] conversation : conversations2) {
            Long idMusicien1 = (Long) conversation[0];
            String nom = (String) conversation[1];
            String photo = (String) conversation[2];
            Date date = (Date) conversation[3];
            String message = (String) conversation[4];
            if (!containsConversation(conversationDtos1, idMusicien1, nom)) {
                ConversationParMusicienDto conversationDto = new ConversationParMusicienDto();
                conversationDto.setId(idMusicien1);
                conversationDto.setNom(nom);
                conversationDto.setPhoto(photo);
                conversationDto.setDate(date);
                conversationDto.setMessage(message);
                // creation de la deuxieme requete
                conversationDtos1.add(conversationDto);
            }
        }
        return conversationParMusicienDto(conversationDtos1);
    }

    /**
     * permet de verifier si des elements id et nom sont contenus dans une liste
     * dans le sens si la liste est remplie
      * @param conversationDtos
     * @param id
     * @param nom
     * @return
     */
    private boolean containsConversation(List<ConversationParMusicienDto> conversationDtos, Long id, String nom) {
        for (ConversationParMusicienDto conversationDto : conversationDtos) {
            if (conversationDto.getId().equals(id) && conversationDto.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }

    /**
     * renvoi une liste de ConversationParMusicienDto
     * sans doublons avec le dernier message reçu et la date
     */
    private List<ConversationParMusicienDto> conversationParMusicienDto(List<ConversationParMusicienDto> conversationParMusicienDto){
        // tri la conversation par date decroissante
        Collections.sort(conversationParMusicienDto, Comparator.comparing(ConversationParMusicienDto::getDate).reversed());

        Boolean creer = false;
        Boolean fisrt = true; // le premier est toujours crée, permet de boucler ensuite dessus
        List<ConversationParMusicienDto> uniqueConversations = new ArrayList<>();
        for (ConversationParMusicienDto conversation: conversationParMusicienDto) {
                ConversationParMusicienDto resultat = new ConversationParMusicienDto();
                if (fisrt) {
                    uniqueConversations.add(conversation);
                    fisrt = false;
                } else {
                    for (ConversationParMusicienDto uniqueConversation : uniqueConversations) {
                        if (uniqueConversation.getId() != conversation.getId()) {
                            resultat.setId(conversation.getId());
                            resultat.setNom(conversation.getNom());
                            resultat.setMessage(conversation.getMessage());
                            resultat.setPhoto(conversation.getPhoto());
                            resultat.setDate(conversation.getDate());
                            creer = true;
                        }
                    }
                }
                if (creer) {
                    uniqueConversations.add(resultat);
                    creer = false;
                    break;
                }
        }
        return uniqueConversations;
    }


    /**
     * Recupere tous les messages correspondants à l'user1 et l'user2
     * 1re requete sur le user1
     * 2ème requete sur le user2
     * @param id1 user1_id
     * @param id2 user2_id
     * @return liste des conversations par utilisateur avec un autre utilisateur
     */
    public List<ConversationDetailDto> getAllConversByid1AndId2(Long id1, Long id2) {
        // cree deux listes pour les ajouter ensemble
        List<Object[]> conversationData = conversationRepository.findByUser1IdAndUser2Id(id1, id2);
        List<ConversationDetailDto> dtos = new ArrayList<>();

        for (Object[] conversation : conversationData) {
            ConversationDetailDto dto = new ConversationDetailDto();
            dto.setId((Long) conversation[0]);
            dto.setMusicien1_id((Long) conversation[1]);
            dto.setMusicien2_id((Long) conversation[2]);
            dto.setMessage((String) conversation[3]);
            dto.setDate((Date) conversation[4]);
            dto.setPhoto((String) conversation[5]);
            dtos.add(dto);
        }
        return dtos;

    }
//        List <Conversation> conversations = conversationRepository.findByUser1IdAndUser2Id(id1, id2);
//        return conversations.stream().map(convert -> mapper.convertValue(convert, ConversationDetailDto.class)).toList();
    // return conversationRepository.findByUser1IdAndUser2Id(id1, id2);

    /**
     * creation d'une conversation
     * @param conversation
     * @return
     */
    public Conversation createConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    /**
     * cree une conversation en utilisant des utilisateurs existants
     * utilise par le controlleurSocket pour creer un message instantannée
     * @param conversationRequestDto modele minimum reçu du back : message + les id
     * @return
     */
    public Conversation createConversationWithMusiciens(ConversationRequestDto conversationRequestDto) {
        Long user1_id = conversationRequestDto.getMusicien1_id();
        Long user2_id = conversationRequestDto.getMusicien2_id();

        // Vérifie si les utilisateurs avec les IDs spécifiés existent
        Musicien musicien1 = musicienRepository.findById(user1_id).orElse(null);
        Musicien musicien2 = musicienRepository.findById(user2_id).orElse(null);

        // si user1 et 2 user2 pas null
        // on cree une nouvelle conversationEntity
        // sinon renvoi l'erreur "L'un ou les deux utilisateurs n'existent pas."
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
     * @return conversation à l'id indiquée
     */
    public Conversation updateConversation(Long id, Conversation conversation) {
        return conversationRepository.findById(id)
                .map(message-> {
                    message.setMessage(conversation.getMessage());
                    message.setDate(conversation.getDate());
                    return conversationRepository.save(conversation);
                }).orElseThrow(()-> new RuntimeException("Conversation introuvable !"));
        // on cherche la conversation par son id, si l'id n'existe pas on retourne le message d'erreur "Conversation introuvable"
        //sinon on retourne la conversation se trouvant à l'id recherché
    }

    /**
     * Supprime une conversation
     * @param id de la conversation à supprimer
     * @return
     */
    public void deleteConversation(Long id) {
        conversationRepository.deleteById(id);
    }



    public void deleteAllConversation(){
        conversationRepository.deleteAll();
    }

}
