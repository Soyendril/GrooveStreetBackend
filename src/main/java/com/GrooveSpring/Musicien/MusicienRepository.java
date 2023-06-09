package com.GrooveSpring.Musicien;

import com.GrooveSpring.Instrument.Instrument;
import com.GrooveSpring.Musicien.dto.MusicienAuthDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicienRepository extends JpaRepository<Musicien, Long> {
    /**
     * Retourne un musicien avec meme pseudo et meme mot de passe
     * @param email
     * @param password
     * @return
     */
    Musicien findByEmailAndPassword(String email, String password);


//    @Query("SELECT m.id, m.nom, m.pseudo, m.email FROM Musicien m WHERE m.email = :email AND m.password = :password")
    @Query("SELECT m FROM Musicien m WHERE m.email = :email AND m.password = :password")
    Musicien findMusicienIdByEmailAndPassword(@Param("email") String email, @Param("password") String password);


    @Query("SELECT m.id FROM Musicien m")
    List<Long> findAllMusicienIds();


    /**
     * recupere uniquement le pseudo
     * @param id
     * @return
     */
    @Query("SELECT m.pseudo FROM Musicien m WHERE m.id = :id")
    String findPseudoById(Long id);

    @Query("SELECT m.codePostal FROM Musicien m WHERE m.codePostal = :codePostal")
    List<Musicien> findMusiciensByCodePostal(@Param("codePostal") String codePostal);


    @Query("SELECT m.codePostal FROM Musicien m WHERE m.codePostal = :codePostal AND m.instrument = :instrument")
    List<Musicien> findMusiciensByCodePostalAndInstrument(@Param("codePostal") String codePostal, @Param("instrument") Instrument instrument);

    @Query("SELECT m.codePostal FROM Musicien m WHERE m.instrument = :instrument")
    List<Musicien> findMusiciensByInstrument(@Param("instrument") Instrument instrument);



}
