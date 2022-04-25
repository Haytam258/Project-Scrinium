package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conges {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private int nbrJours;
    private boolean reponse;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
}
