package com.GrooveSpring.conversation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    /**
     * liste et détail des conversations pour un id
     * @param musicien1_id
     * @return
     */
    List<Conversation> findByMusicien1Id(Long musicien1_id);

    /**
     * lite des conversations pour un id avec tous les autres
     * recherche id envoyeur et id receveur du message
     * @param musicien1Id
     * @return
     */

    @Query("SELECT c.musicien2.id, c.musicien2.nom, c.musicien2.photo FROM Conversation c WHERE c.musicien1.id = :musicien1Id GROUP BY c.musicien2.id, c.musicien2.nom")
    List<Object[]> findConversationGroupByMusicien1(Long musicien1Id);

    /**
     * lite des conversations pour un id avec tous les autres
     * recherche id envoyeur et id receveur du message
     * @param musicien2Id
     * @return
     */
    @Query("SELECT c.musicien1.id, c.musicien1.nom, c.musicien1.photo FROM Conversation c WHERE c.musicien2.id = :musicien2Id GROUP BY c.musicien1.id, c.musicien1.nom")
    List<Object[]> findConversationGroupByMusicien2(Long musicien2Id);

    /**
     * Recupere la date du dernier message avec son contenu
     * @param musicien1Id, musicien2Id
     * @return
     */
    // @Query("SELECT c.musicien2.id, c.musicien2.nom, c.musicien2.photo FROM Conversation c WHERE c.musicien1.id = :musicien1Id and c.musicien2.id = :musicien2Id order by c.date desc LIMIT 1", nativeQuery=true)

    @Query(value = "SELECT c.date, c.message, c.musicien1.id, c.musicien2.id " +
            "FROM conversations c " +
            "WHERE c.musicien1.id = :musicien1Id AND c.musicien2.id = :musicien2Id " +
            "ORDER BY c.date DESC " +
            "LIMIT 1", nativeQuery = true)

    Conversation findFirstMusicien1AndMusicien2ByDateDesc(Long musicien1Id, Long musicien2Id);



    /**
     * recherche toutes les conversations avec deux id données
     * conversation complète entre user1 et user2
     * verifie chaque id dans les deux colones user_id car peut envoyer(en user1) ou recevoir(en user2)
     * @param musicien1Id
     * @param musicien2Id
     * @return
     */
    @Query("SELECT c FROM Conversation c WHERE (c.musicien1.id = :musicien1Id OR c.musicien2.id = :musicien1Id) AND (c.musicien1.id = :musicien2Id OR c.musicien2.id = :musicien2Id) ORDER BY c.date")
    List<Conversation> findByUser1IdAndUser2Id(Long musicien1Id, Long musicien2Id);

    /**
     *
     */
    @Query("SELECT c.musicien2.id, c.musicien2.nom, c.musicien2.photo, c.date, c.message FROM Conversation c WHERE c.musicien1.id = :musicien1Id order by c.date desc")
    List<Object[]> findConversationByMusicien1(Long musicien1Id);

    @Query("SELECT c.musicien1.id, c.musicien1.nom, c.musicien1.photo, c.date, c.message FROM Conversation c WHERE c.musicien2.id = :musicien1Id order by c.date desc")
    List<Object[]> findConversationByMusicien2(Long musicien1Id);

}
