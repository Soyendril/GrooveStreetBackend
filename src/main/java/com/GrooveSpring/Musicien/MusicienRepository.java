package com.GrooveSpring.Musicien;

import com.GrooveSpring.Musicien.dto.MusicienAuthDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicienRepository extends JpaRepository<Musicien, Long> {
    /**
     * Retourne un musicien avec meme pseudo et meme mot de passe
     * @param email
     * @param password
     * @return
     */
    Musicien findByEmailAndPassword(String email, String password);

    /**
     * Permet de retourner l'id de l'utilisateur demandé
     * @param email
     * @param password
     * @return id
     */

    @Query("SELECT m.id FROM Musicien m WHERE m.email = :email AND m.password = :password")
    Long findMusicienIdByEmailAndPassword(@Param("email") String email, @Param("password") String password);


}
