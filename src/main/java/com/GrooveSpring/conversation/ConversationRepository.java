package com.GrooveSpring.conversation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    /**
     * liste et détail des conversations pour un id
     * @param user1_id
     * @return
     */
    List<Conversation> findByUser1Id(Long user1_id);

    /**
     * lite des conversations pour un id avec tous les autres
     * recherche id envoyeur et id receveur du message
     * @param user1Id
     * @return
     */
    @Query("SELECT c FROM Conversation c WHERE c.user1.id = :user1Id OR c.user2.id = :user1Id GROUP BY c.user1.id, c.user2.id")
    List<Conversation> findConversationGroupByUser2(Long user1Id);


    /**
     * recherche toutes les conversations avec deux id données
     * conversation complète entre user1 et user2
     * verifie chaque id dans les deux colones user_id car peut envoyer(en user1) ou recevoir(en user2)
     * @param user1Id
     * @param user2Id
     * @return
     */
    @Query("SELECT c FROM Conversation c WHERE (c.user1.id = :user1Id OR c.user2.id = :user1Id) AND (c.user.id = :user2Id OR c.user2.id = :user2Id) ORDER BY c.date")
    List<Conversation> findByUser1IdAndUser2Id(Long user1Id, Long user2Id);
}
