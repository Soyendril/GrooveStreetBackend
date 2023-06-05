package com.GrooveSpring.Instrument;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping(value = "instruments")
public class InstrumentController {

    private final InstrumentService instrumentService;

    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    /**
     * recherche de tous les instruments
     * @return
     */
    @GetMapping("")
    public List<Instrument> findAll(){
        return instrumentService.findAll();
    }

    /**
     * recherche d'instrument par id
     * @param id de l'instrument à trouver
     * @return
     */
    @GetMapping("/{id}")
    public Instrument findById(@PathVariable Long id){
        return instrumentService.findById(id);
    }

    @PostMapping("")
    public Instrument save(@RequestBody Instrument instrument){
        return instrumentService.save(instrument);
    }

    /**
     * mise à jour d'un instrument
     * @param instrument
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Instrument update(@RequestBody Instrument instrument,@PathVariable Long id){
        return instrumentService.update(instrument, id);
    }

    /**
     * suppression d'instrument en fonction de son id
     * @param id de l'instrument a supprimer
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        instrumentService.deleteById(id);
    }
}
