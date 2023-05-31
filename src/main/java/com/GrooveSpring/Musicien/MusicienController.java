package com.GrooveSpring.Musicien;

import com.GrooveSpring.Musicien.Musicien;
import com.GrooveSpring.Musicien.MusicienService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    /**
     * Utiliser @reqyestparam a la place de @pathvariable
     * @param pseudo
     * @param password
     * @return
     */
    @GetMapping("{userName}/{password}")
    // @GetMapping("/read")
    public Map<String, Object> getMusicienPseudo(@PathVariable String pseudo, @PathVariable String password){
        return musicienService.getAuth(pseudo, password);
    }
}
