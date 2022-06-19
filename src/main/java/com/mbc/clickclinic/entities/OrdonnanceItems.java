package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class OrdonnanceItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String posologie; //Nombre de géllule à prendre à la fois par exemple
    private int nbrFoisParJour;
    private String dureeTraitement;
    private int dose;

    @OneToOne
    @JoinColumn(name = "medicament_id")
    @JsonBackReference("medicament_items")
    private Medicament medicament;

    @ManyToOne
    @JoinColumn(name = "ordonnance_id")
    @JsonBackReference("ordonnance_items")
    private Ordonnance ordonnance;
}
