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

    @GetMapping("")
    public List<Media> findAll(){
        return mediaService.findAll();
    }

    @GetMapping("/{id}")
    public Media findById(@PathVariable Long id){
        return mediaService.findById(id);
    }

    @PostMapping("")
    public Media save(@RequestBody Media media){
        return mediaService.save(media);
    }

    @PutMapping("/{id}")
    public Media update(@RequestBody Media media, @PathVariable Long id){ return  mediaService.update(media,id);}

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        mediaService.deleteById(id);
    }

}
