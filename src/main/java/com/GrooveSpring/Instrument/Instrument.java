package com.GrooveSpring.Instrument;

import com.GrooveSpring.Musicien.Musicien;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "instruments")
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nom;
    @Column
    private String type;

    @ManyToMany(mappedBy = "instrument")
    @JsonBackReference
    private List<Musicien> musicien;

    public Instrument() {
    }
}
