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

    @GetMapping("")
    public List<Instrument> findAll(){
        return instrumentService.findAll();
    }

    @GetMapping("/{id}")
    public Instrument findById(@PathVariable Long id){
        return instrumentService.findById(id);
    }

    @PostMapping("")
    public Instrument save(@RequestBody Instrument instrument){
        return instrumentService.save(instrument);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        instrumentService.deleteById(id);
    }
}
