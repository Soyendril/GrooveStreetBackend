package com.GrooveSpring.Instrument;

import com.GrooveSpring.Musicien.Musicien;
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

    @ManyToMany(mappedBy = "musiciens")
    private List<Musicien> musiciens;

    public Instrument() {
    }
}
