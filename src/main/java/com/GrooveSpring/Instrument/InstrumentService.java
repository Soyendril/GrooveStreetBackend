package com.GrooveSpring.Instrument;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public InstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public List<Instrument> findAll(){
        return instrumentRepository.findAll();
    }

    public Instrument findById(Long id){
        return instrumentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Instrument introuvable"));
    }

    public Instrument save(Instrument instrument){
        return instrumentRepository.save(instrument);
    }

    public void deleteById(Long id){
        instrumentRepository.deleteById(id);
    }
}
