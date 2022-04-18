package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Agenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String jour;
    private int heureDebut;
    private int heureFin;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medecin_id")
    @JsonBackReference(value = "medecin_agenda")
    private Medecin medecin;
}
