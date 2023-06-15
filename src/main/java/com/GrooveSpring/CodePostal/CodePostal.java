package com.GrooveSpring.CodePostal;

import com.GrooveSpring.Musicien.Musicien;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "commune")
    private String commune;

    @Column(name = "zipcode")
    private String zipcode;

    @OneToMany(mappedBy = "codePostal")
    @JsonBackReference
    private List<Musicien> musiciens;

    /**
     * getDepartement() méthode qui permet de recuperer le departement en fonction du zipcode
     * @return
     */
    public String getDepartement() {
        if (zipcode != null) {// vérification que le codepostal ne soit pas null
            if (zipcode.length() == 4) {
                zipcode= "0" + zipcode;
                // verification de la taille du codePostal
                // prise en compte du fait que les 10 premiers commencent par un 0
                // ajout d'un 0 au debut du code postal
            }
            if (zipcode.length() >= 2) {
                return zipcode.substring(0, 2);
            }
            // récuperation des deux premiers caractères du codepostal afin de dégager le departement
        }
        return null;
        }


}
