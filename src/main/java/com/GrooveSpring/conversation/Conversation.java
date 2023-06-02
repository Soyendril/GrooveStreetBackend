package com.GrooveSpring.conversation;

import com.GrooveSpring.Musicien.Musicien;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Musicien musicien1;

    @ManyToOne
    @JoinColumn(name = "musicien2_id")
    @JsonIgnoreProperties({"instrument", "instrumentRecherche"})
    private Musicien musicien2;

    @Column(length = 250)
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private Date date;

}
