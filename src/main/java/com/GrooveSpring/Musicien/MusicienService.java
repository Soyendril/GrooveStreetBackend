package com.GrooveSpring.Musicien;

import com.GrooveSpring.outils.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MusicienService {
    private final MusicienRepository musicienRepository;

    public MusicienService(MusicienRepository musicienRepository) {
        this.musicienRepository = musicienRepository;
    }

    public List<Musicien> findAll() {
        return musicienRepository.findAll();
    }

    public Musicien findById(Long id) {
        return musicienRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Musicien introuvable"));
    }

    public List<Long> getMusicienIds(){
        return musicienRepository.findAllMusicienIds();
    }

    public Musicien save(Musicien musicien) {
        return musicienRepository.save(musicien);
    }

    public Musicien update(Musicien musicien, long id) {
        Musicien existingMusicien = musicienRepository.findById(id).orElse(null);

        if (existingMusicien == null) {
            throw new RuntimeException("Musicien not found");
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
        }
    }

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

    public ResponseEntity<?> returnIsAuth(Musicien musicien) {
        if (musicienRepository.findByEmailAndPassword(musicien.getEmail(), musicien.getPassword()) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mauvais email ou password");
        }

        // Votre logique de traitement si la requête est valide
        Long musicienId = musicienRepository.findMusicienIdByEmailAndPassword(musicien.getEmail(), musicien.getPassword());
        AuthResponse successResponse = new AuthResponse("Requête traitée avec succès", musicienId);
        return ResponseEntity.ok(successResponse);
    }

}