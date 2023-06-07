package MusicienMatcher;

import com.GrooveSpring.Instrument.Instrument;
import com.GrooveSpring.Musicien.Musicien;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de faire le tri en fonction des filtres choisi par le musicien
 * deux filtres possible:
 * - filtre par style musical avec la méthode isMatchingStyle()
 * - filtre par localisation avec la méthode isMatchingDepartement()
 */
@Component
public class MusicienMatcher {
    /**
     * méthode permettant le matching des profils de musiciens
     * @param musicien1 musicien effectuant la recherche
     * @param allMusiciens liste de tous les musiciens
     * @return liste des musiciens correspondant aux critères de recherche du musicien 1
     */
    public List<Musicien> matchMusiciens(Musicien musicien1, List<Musicien> allMusiciens) {
        List<Musicien> matchedMusiciens = new ArrayList<>();

        for (Musicien musicien2 : allMusiciens) {
            //Si les styles musicaux et les departements des musiciens correspondent,
            //on ajoute le musicien 2 à la liste de musiciens répondant aux critères du musicien 1
            if (isMatchingStyle(musicien2, musicien1) && isMatchingDepartement(musicien2, musicien1)) {
                matchedMusiciens.add(musicien2);
            }
        }

        return matchedMusiciens;
    }

    /**
     * isMatchingStyle() méthode qui permet de vérifier si les deux musiciens ont le même style musical.
     * @param musicien musicien effectuant la recherche
     * @param targetMusicien musicien ciblé potentiel ciblé par la recherche
     * @return booléen qui rend le résultat de la vérification du style.
     */

    private boolean isMatchingStyle(Musicien musicien, Musicien targetMusicien) {
        // si les style musicaux des deux musiciens sont differents on retourne false
        if (!musicien.getStyle().equals(targetMusicien.getStyle())) {
            return false;
        }
        List<Instrument> instrumentsRecherches = targetMusicien.getInstrument();
        for (Instrument instrumentRecherche : instrumentsRecherches) {
            // si le targetMusicien ne possede pas l'instrument recherché on retourne false
            if (!musicien.getInstrument().contains(instrumentRecherche)) {
                return false;
            }
        }
        // on retourne true dans les autres cas car cela signifie que les style des deux musiciens correspondent
        // et que le targetMusicien possède bien l'instrument recherché par le musicien
        // ce qui signifie que le targetMusicien correspond bien à tous les critères de recherche du musicien
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


