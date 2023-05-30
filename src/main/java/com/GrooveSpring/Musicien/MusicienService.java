package com.GrooveSpring.Musicien;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
            existingMusicien.setAge(musicien.getAge());
            existingMusicien.setDescription(musicien.getDescription());
            existingMusicien.setPhoto(musicien.getPhoto());
            existingMusicien.setInstruments(musicien.getInstruments());
            existingMusicien.setCodePostal(musicien.getCodePostal());
            existingMusicien.setPseudo(musicien.getPseudo());
            existingMusicien.setStyle(musicien.getStyle());
            return musicienRepository.save(existingMusicien);
        }
    }

        public void deleteById (Long id){
            musicienRepository.deleteById(id);
        }
    }
