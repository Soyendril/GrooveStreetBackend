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

    /**
     * recherche de tous les medias
     * @return liste de tous les medias
     */
    public List<Media> findAll(){
        return mediaRepository.findAll();
    }

    /**
     * recherche de media par id
     * @param id du media à trouver
     * @return
     */
    public Media findById(Long id){
        return mediaRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Media introuvable"));
        // on cherche le media par son id, si l'id n'existe pas on retourne le message d'erreur "Media introuvable"
        //sinon on retourne le media se trouvant à l'id recherché
    }

    public Media save(Media media){
        return mediaRepository.save(media);
    }

    /**
     * mise à jour d'un media
     * @param media à mettre à jour
     * @param id du media à mettre a jour
     * @return
     */
    public Media update(Media media, Long id){
        Media existingMedia = mediaRepository.findById(id).orElse(null);
        if (existingMedia==null){
            throw new RuntimeException("Media not found");
            //si le media n'existe pas on retourne le message "Media not found"
        } else {
            existingMedia.setNom(media.getNom());
            existingMedia.setLien(media.getLien());
            existingMedia.setType(media.getType());
            return mediaRepository.save(existingMedia);
            //sinon on applique les mise à jour faites au media
        }
    }

    /**
     * supression d'un media en fonction de son id
     * @param id du media à supprimer
     */
    public void deleteById(Long id){
        mediaRepository.deleteById(id);
    }

}
