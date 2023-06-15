package com.GrooveSpring.conversation;

import com.GrooveSpring.Musicien.Musicien;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "musicien1_id")
    @JsonIgnoreProperties({"instrument", "instrumentRecherche"})
    @JsonManagedReference
    private Musicien musicien1;

    @ManyToOne
    @JoinColumn(name = "musicien2_id")
    @JsonIgnoreProperties({"instrument", "instrumentRecherche"})
    @JsonManagedReference
    private Musicien musicien2;

    @Column(length = 250)
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

}
