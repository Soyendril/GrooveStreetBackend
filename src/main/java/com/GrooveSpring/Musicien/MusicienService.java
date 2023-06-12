package com.GrooveSpring.Musicien;

import com.GrooveSpring.Instrument.Instrument;
import com.GrooveSpring.Musicien.dto.MusicienAuthDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MusicienService {
    private final MusicienRepository musicienRepository;

    private final ObjectMapper objectMapper;

    public MusicienService(MusicienRepository musicienRepository,ObjectMapper objectMapper) {
        this.musicienRepository = musicienRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * recherche de tous les musiciens
     * @return liste des tous les musiciens
     */
    public List<Musicien> findAll() {
        return musicienRepository.findAll();
    }

    /**
     * recherche de musicien par id
     * @param id du musicien à trouver
     * @return musicien à l'id indiquée
     */
    public Musicien findById(Long id) {
        return musicienRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Musicien introuvable"));
        // on cherche le musicien par son id, si l'id n'existe pas on retourne le message d'erreur "Musicien introuvable"
        //sinon on retourne le musicien se trouvant à l'id recherché
    }

    public List<Long> getMusicienIds(){
        return musicienRepository.findAllMusicienIds();
    }

    /**
     * findMusiciensByCodePostal()
     * méthode qui permet de trouver la liste des musiciens se trouvant dans le codePostal indiqué
     * @param codePostal
     * @return
     */
    public List<Musicien> findMusiciensByCodePostal(String codePostal){
        return  musicienRepository.findMusiciensByCodePostal(codePostal);
    }

    public Musicien findMusicienByPseudo(String pseudo){
        return  musicienRepository.findMusicienByPseudo(pseudo);
    }
    public List<Musicien> findMusiciensByCodePostalAndInstrument(String codePostal, Instrument instrument){
        return  musicienRepository.findMusiciensByCodePostalAndInstrument(codePostal,instrument);
    }

    public List<Musicien> findMusiciensByInstrument( Instrument instrument){
        return  musicienRepository.findMusiciensByInstrument(instrument);
    }
    public Musicien save(Musicien musicien) {
        return musicienRepository.save(musicien);
    }

    /**
     * Update d'un musicien
     * @param musicien musicien à update
     * @param id id du musicien à update
     * @return
     */
    public Musicien update(Musicien musicien, long id) {
        Musicien existingMusicien = musicienRepository.findById(id).orElse(null);

        if (existingMusicien == null) {
            throw new RuntimeException("Musicien not found");
            //si le musicien n'existe pas on retourne le message "Musicien not found"
        } else {
            existingMusicien.setNom(musicien.getNom());
            existingMusicien.setEmail(musicien.getEmail());
            existingMusicien.setCodePostal(musicien.getCodePostal());
            existingMusicien.setAge(musicien.getAge());
            existingMusicien.setDescription(musicien.getDescription());
            existingMusicien.setPseudo(musicien.getPseudo());
            existingMusicien.setPhoto(musicien.getPhoto());
            existingMusicien.setStyle(musicien.getStyle());
            existingMusicien.setInstrument(musicien.getInstrument());
            return musicienRepository.save(existingMusicien);
            //sinon on applique les mise à jour faites au media
        }
    }

    /**
     * deleteById() méthode qui permet la supression d'un musicien à l'id indiquée
     * @param id du musicien à supprimer
     */
        public void deleteById (Long id){
            musicienRepository.deleteById(id);
        }


    /**
     * Teste un utilisateur existe dans la base avec l'email et le meme mot de passe
     * @param email
     * @param password
     * @return id + username de l'utilisateur
     */
    public Map<String, Object> getAuth(String email, String password) {
        Musicien musicien = musicienRepository.findByEmailAndPassword(email, password);
        Map<String, Object> response = new HashMap<>();
        if (musicien == null) {
            // La requête est vide, aucun utilisateur trouvé
            response.put("id", "not");
            response.put("email", "not");
            return response;
        }
        response.put("id", musicien.getId());
        response.put("email", musicien.getEmail());
        return response;
    }

    /**
     * utilisé pour l'authentification
     * recherche un match email/password
     * renvoi une réponse s'il trouve ou non + l'id du musicien si trouvé
     * @param musicien
     * @return
     */
    public Musicien returnIsAuth(Musicien musicien) {
        if (musicienRepository.findByEmailAndPassword(musicien.getEmail(), musicien.getPassword()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "profil non trouvé");
        }
        musicien = musicienRepository.findMusicienIdByEmailAndPassword(musicien.getEmail(), musicien.getPassword());
        musicien.setPassword("*****");

        return musicien;
    }

    public Musicien findByIdInfos(Long id) {
        return musicienRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Porfil non trouvé")
        );
    }

}