package com.GrooveSpring.Instrument;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public InstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    /**
     * recherche de tous les instruments
     * @return liste de tous les instruments
     */
    public List<Instrument> findAll(){
        return instrumentRepository.findAll();
    }

    /**
     * recherche d'un instrument par id
     * @param id de l'instrument à trouver
     * @return instrument à l'id indiquée
     */
    public Instrument findById(Long id){
        return instrumentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Instrument introuvable"));
        // on cherche l'instrument par son id, si l'id n'existe pas on retourne le message d'erreur "Instrument introuvable"
        //sinon on retourne l'instrument se trouvant à l'id recherché
    }

    public Instrument save(Instrument instrument){
        return instrumentRepository.save(instrument);
    }

    /**
     * mise à jour d'un instrument
     * @param instrument à modifier
     * @param id de l'instrument à modifier
     * @return
     */
    public Instrument update(Instrument instrument, long id){
        Instrument existingIns = instrumentRepository.findById(id).orElse(null);
        if (existingIns == null){
            throw new RuntimeException("Instrument not found");
            //si l'instrument n'existe pas on retourne le message "Instrument not found"
        } else {
            existingIns.setNom(instrument.getNom());
            existingIns.setType(instrument.getType());
            return instrumentRepository.save(existingIns);
            //sinon on applique les mise à jour faites à l'instrument
        }
    }

    /**
     * suppression d'un instrument par id
     * @param id de l'instrument à supprimer
     */
    public void deleteById(Long id){
        instrumentRepository.deleteById(id);
    }
}
