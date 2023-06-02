package com.GrooveSpring.Musicien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicienRepository extends JpaRepository<Musicien, Long> {
    /**
     * Retourne un musicien avec meme pseudo et meme mot de passe
     * @param pseudo
     * @param password
     * @return
     */
    Musicien findByEmailAndPassword(String email, String password);
}
