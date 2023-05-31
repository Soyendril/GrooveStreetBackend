package com.GrooveSpring.Media;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MediaService {
    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<Media> findAll(){
        return mediaRepository.findAll();
    }

    public Media findById(Long id){
        return mediaRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Media introuvable"));
    }

    public Media save(Media media){
        return mediaRepository.save(media);
    }

    public Media update(Media media, Long id){
        Media existingMedia = mediaRepository.findById(id).orElse(null);
        if (existingMedia==null){
            throw new RuntimeException("Média non existant");
        } else {
            existingMedia.setNom(media.getNom());
            existingMedia.setLien(media.getLien());
            existingMedia.setType(media.getType());
            return mediaRepository.save(existingMedia);
        }
    }

    public void deleteById(Long id){
        mediaRepository.deleteById(id);
    }

}
