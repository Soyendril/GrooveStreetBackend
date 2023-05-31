package MusicienMatcher;

import com.GrooveSpring.Instrument.Instrument;
import com.GrooveSpring.Musicien.Musicien;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MusicienMatcher {
    public List<Musicien> matchMusiciensStyle(Musicien musicien1, List<Musicien> allMusiciens) {
        List<Musicien> matchedMusiciens = new ArrayList<>();

        for (Musicien musicien2 : allMusiciens) {
            if (isMatchingStyle(musicien2, musicien1)) {
                matchedMusiciens.add(musicien2);
            }
        }

        return matchedMusiciens;
    }

    private boolean isMatchingStyle(Musicien musicien, Musicien targetMusicien) {
        if (!musicien.getStyle().equals(targetMusicien.getStyle())) {
            return false;
        }
        List<Instrument> instrumentsRecherches = targetMusicien.getInstruments();
        for (Instrument instrumentRecherche : instrumentsRecherches) {
            if (!musicien.getInstruments().contains(instrumentRecherche)) {
                return false;
            }
        }
        return true;
    }
}


