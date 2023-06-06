package MusicienMatcher;

import com.GrooveSpring.Instrument.Instrument;
import com.GrooveSpring.Musicien.Musicien;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

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

    private boolean isMatchingDepartement(Musicien musicien, Musicien targetMusicien){
        String codePostal1 = musicien.getCodePostal();
        String codePostal2 = targetMusicien.getCodePostal();
        return codePostal1.substring(0, 2).equals(codePostal2.substring(0, 2));
    }
}


