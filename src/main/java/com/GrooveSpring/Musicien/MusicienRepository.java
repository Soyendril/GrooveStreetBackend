package com.GrooveSpring.Musicien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicienRepository extends JpaRepository<Musicien, Long> {
    /**
     * Retourne un musicien avec meme pseudo et meme mot de passe
     * @param
     * @param password
     * @return
     */
    Musicien findByEmailAndPassword(String email, String password);

    @Query("SELECT m.id FROM Musicien m")
    List<Long> findAllMusicienIds();
}
