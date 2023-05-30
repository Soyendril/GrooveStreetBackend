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

    /**
     * findAll() méthode qui retourne tous la liste de musicien
     * @return
     */

    @GetMapping("")
    public List<Musicien> findAll(){
        return musicienService.findAll();
    }

    /**
     * findById() méthode qui retourne un musicien en fonction de son id
     * @param id id du musicien à trouver
     * @return
     */
    @GetMapping("/{id}")
    public Musicien findById(@PathVariable Long id){
        return musicienService.findById(id);
    }

    @PostMapping("")
    public Musicien save(@RequestBody Musicien musicien){
        return musicienService.save(musicien);
    }

    /**
     * Update d'un musicien
     * @param musicien musicien à update
     * @param id id du musicien à update
     * @return
     */
    @PutMapping("/{id}")
    public Musicien update(@RequestBody Musicien musicien, @PathVariable long id) {
        return musicienService.update(musicien, id);
    }

    /**
     * deleteById() méthode qui supprime un musicien en fonction de son id
     * @param id id du musicien à supprimer
     */

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        musicienService.deleteById(id);
    }
}
