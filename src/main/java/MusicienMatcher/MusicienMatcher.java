package MusicienMatcher;

import com.GrooveSpring.Instrument.Instrument;
import com.GrooveSpring.Musicien.Musicien;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de faire le tri en fonction des filtres choisi par le musicien
 * deux filtres possible:
 * -filtre par style musical avec la méthode isMatchingStyle()
 * -filtre par localisation avec la méthode isMatchingDepartement()
 */
@Component
public class MusicienMatcher {
    public List<Musicien> matchMusiciens(Musicien musicien1, List<Musicien> allMusiciens) {
        List<Musicien> matchedMusiciens = new ArrayList<>();

        for (Musicien musicien2 : allMusiciens) {
            if (isMatchingStyle(musicien2, musicien1) && isMatchingDepartement(musicien2, musicien1)) {
                matchedMusiciens.add(musicien2);
            }
        }

        return matchedMusiciens;
    }

    /**
     * isMatchingStyle() méthode qui permet de vérifier si les deux musiciens ont le même style musical.
     * @param musicien
     * @param targetMusicien
     * @return booléen qui rend le résultat de la vérification du style.
     */

    private boolean isMatchingStyle(Musicien musicien, Musicien targetMusicien) {
        if (!musicien.getStyle().equals(targetMusicien.getStyle())) {
            return false;
        }
        List<Instrument> instrumentsRecherches = targetMusicien.getInstrument();
        for (Instrument instrumentRecherche : instrumentsRecherches) {
            if (!musicien.getInstrument().contains(instrumentRecherche)) {
                return false;
            }
        }
        return true;
    }

    /**
     * isMatchingDepartement() méthode qui permet de vérifier si les deux musiciens sont dans le même département.
     * @param musicien
     * @param targetMusicien
     * @return booléen qui rend le résultat de la vérification du département
     */
    private boolean isMatchingDepartement(Musicien musicien, Musicien targetMusicien){
        String codePostalMusicien = musicien.getCodePostal();
        String codePostalTargetMusicien = targetMusicien.getCodePostal();
        return codePostalMusicien.substring(0, 2).equals(codePostalTargetMusicien.substring(0, 2));
        // vérification des 2 premiers caractères du code postal afin de savoir si les deux musiciens se situent dans le même departement
    }
}


