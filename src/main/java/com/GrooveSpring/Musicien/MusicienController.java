package com.GrooveSpring.Musicien;


import com.GrooveSpring.Instrument.Instrument;
import com.GrooveSpring.Musicien.Musicien;
import com.GrooveSpring.Musicien.MusicienService;
import com.GrooveSpring.Musicien.dto.MusicienAuthDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/musiciens")
public class MusicienController {
    private final MusicienService musicienService;
    private final ObjectMapper mapper;

    public MusicienController(MusicienService musicienService, ObjectMapper mapper) {
        this.musicienService = musicienService;
        this.mapper = mapper;
    }

    /**
     * findAll() méthode qui retourne la liste de tous les musicien
     * @return liste de tous les musiciens
     */

    @GetMapping("")
    public List<Musicien> findAll(){
        return musicienService.findAll();
    }

    /**
     * findById() méthode qui retourne un musicien en fonction de son id
     * @param id id du musicien à trouver
     * @return musicien à l'id indiquée
     */
    @GetMapping("/{id}")
    public Musicien findById(@PathVariable Long id){
        return musicienService.findById(id);
    }

    @GetMapping("/codePostal/{codePostal}")
    public List<Musicien>findMusiciensByCodePostal(@PathVariable String codePostal){
        return  musicienService.findMusiciensByCodePostal(codePostal);
    }

    @GetMapping("/pseudo/{pseudo}")
    public Musicien findMusicienByPseudo(@PathVariable String pseudo){
        return  musicienService.findMusicienByPseudo(pseudo);
    }

    @GetMapping("/instrument/{instrument}")
    public List<Musicien>findMusiciensByInstrument(@PathVariable Instrument instrument){
        return  musicienService.findMusiciensByInstrument(instrument);
    }
    @GetMapping("/{codePostal}/{instrument}")
    public List<Musicien>findMusiciensByCodePostalAndInstrument(@PathVariable String codePostal, @PathVariable Instrument instrument){
        return  musicienService.findMusiciensByCodePostalAndInstrument(codePostal,instrument);
    }
    @GetMapping("/ids")
    public List<Long> getMusicienIds() {
       return musicienService.getMusicienIds();
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

    /**
     * getMusicienEmail() méthode qui recupère l'email d'un musicien
     * @param email
     * @param password
     * @return musicien
     */
    @GetMapping("{email}/{password}")
    public Map<String, Object> getMusicienEmail(@PathVariable String email, @PathVariable String password){
        return musicienService.getAuth(email, password);
    }

    /**
     * Recupere un email et un password en post
     * Renvoi l'id et l'email si ok, sinon erreur
     * @param @requestBody
     * @return
     */
    @PostMapping("{verifAuth}")
    public MusicienAuthDto getMusicienEmail(@RequestBody Musicien musicien){
        Musicien entity = musicienService.returnIsAuth(musicien);
        return mapper.convertValue(entity, MusicienAuthDto.class);
    }

    @GetMapping("/auth/{id}")
    public MusicienAuthDto findByIdInfos(@PathVariable Long id){
        Musicien entity = musicienService.findByIdInfos(id);
        return mapper.convertValue(entity, MusicienAuthDto.class);
    }
}
