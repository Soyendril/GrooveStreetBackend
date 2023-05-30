package com.GrooveSpring.conversation;

import com.GrooveSpring.Musicien.Musicien;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "CONVERSATIONS")
@Getter
@Setter
@NoArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "musicien1_id")
    private Musicien musicien1;

    @ManyToOne
    @JoinColumn(name = "musicien2_id")
    private Musicien musicien2;

    @Column(length = 250)
    private String message;
    private Date date;
}
