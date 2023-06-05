package com.GrooveSpring.Media;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping(value = "medias")
public class MediaController {
    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * recherche de tous les medias
     * @return
     */
    @GetMapping("")
    public List<Media> findAll(){
        return mediaService.findAll();
    }

    /**
     * recherche de media par id
     * @param id du media à trouver
     * @return
     */
    @GetMapping("/{id}")
    public Media findById(@PathVariable Long id){
        return mediaService.findById(id);
    }

    @PostMapping("")
    public Media save(@RequestBody Media media){
        return mediaService.save(media);
    }


    /**
     * mise à jour d'un media
     * @param media à mettre à jour
     * @param id du media à mettre a jour
     * @return
     */
    @PutMapping("/{id}")
    public Media update(@RequestBody Media media, @PathVariable Long id){ return  mediaService.update(media,id);}

    /**
     * supression d'un media en fonction de son id
     * @param id du media à supprimer
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        mediaService.deleteById(id);
    }

}
