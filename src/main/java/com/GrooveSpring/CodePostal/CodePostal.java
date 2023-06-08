package com.GrooveSpring.CodePostal;

import com.GrooveSpring.Musicien.Musicien;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "code_Postal")
@Getter
@Setter
@NoArgsConstructor
public class CodePostal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datasetid")
    private String datasetid;

    @Column(name = "recordid")
    private String recordid;

    @Column(name = "code_postal")
    private String code_postal;

    @Column(name = "libelle_d_acheminement")
    private String libelle_d_acheminement;

    @Column(name = "code_commune_insee")
    private String code_commune_insee;

    @Column(name = "coordonnees_geographiques")
    private double[] coordonnees_geographiques;

    @Column(name = "nom_de_la_commune")
    private String nom_de_la_commune;

    @Column(name = "type")
    private String type;

    @Column(name = "coordinates")
    private double[] coordinates;

    @Column(name = "record_timestamp")
    private String record_timestamp;

    @OneToMany(mappedBy = "codePostal", cascade = CascadeType.ALL)
    private List<Musicien> musiciens;

    public String getDepartement() {
        if (code_postal != null && code_postal.length() >= 2) {
            return code_postal.substring(0, 2);
        } else {
            return null;
        }
    }

}
