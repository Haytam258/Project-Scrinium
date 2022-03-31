package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String motif;
    private String antecedent;
    private String diagnostique;
    private String ResultatExmentClinique;
    private double poids;
    private double talle;
    private double imc;
    private double temperature;
    private int frequenceCardiaque;
    private String pressionArterielle;
    private String observation;

}
