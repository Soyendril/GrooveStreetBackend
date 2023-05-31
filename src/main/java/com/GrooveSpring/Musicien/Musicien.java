package com.GrooveSpring.Musicien;

import com.GrooveSpring.Instrument.Instrument;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "musiciens")
public class Musicien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nom;
    @Column
    private String email;
    @Column
    private String codePostal;
    @Column
    private int age;
    @Column
    private String description;
    @Column
    private String pseudo;
    @Column
    private String photo;
    @Column
    private String style;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "musicien_instrument",
            joinColumns = @JoinColumn(name = "musicien_id"),
            inverseJoinColumns = @JoinColumn(name = "instrument_id")
    )

    private List<Instrument> instruments;

    @ManyToMany
    @JoinTable(
            name = "musicien_instrumentRecherche",
            joinColumns = @JoinColumn(name = "musicien_id"),
            inverseJoinColumns = @JoinColumn(name = "instrument_id")
    )
    private List<Instrument> instrumentRecherche;

    @OneToMany
    @JsonBackReference
    private List<Musicien> profilsSuivi;
    public Musicien() {
    }
}
