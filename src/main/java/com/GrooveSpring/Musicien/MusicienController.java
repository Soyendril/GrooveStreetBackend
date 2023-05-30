package com.GrooveSpring.Musicien;

import com.GrooveSpring.Musicien.Musicien;
import com.GrooveSpring.Musicien.MusicienService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "instruments")
public class MusicienController {
    private final MusicienService musicienService;

    public MusicienController(MusicienService musicienService) {
        this.musicienService = musicienService;
    }

    @GetMapping("")
    public List<Musicien> findAll(){
        return musicienService.findAll();
    }

    @GetMapping("/{id}")
    public Musicien findById(@PathVariable Long id){
        return musicienService.findById(id);
    }

    @PostMapping("")
    public Musicien save(@RequestBody Musicien musicien){
        return musicienService.save(musicien);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        musicienService.deleteById(id);
    }
}
