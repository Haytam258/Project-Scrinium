package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;
    private String jour;
    private int heureDebut;
    private int heureFin;
    private String description;

    //Statut : 1 = Vacances, Fournisseurs etc (Pas disponible), Statut : 2 = Travail (Disponible),
    private int statut;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medecin_id")
    @JsonBackReference(value = "medecin_agenda")
    private Medecin medecin;
}
