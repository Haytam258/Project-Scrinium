package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Rendezvous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date dateRv;
    private String heure;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    @JsonBackReference(value = "medecin_rendez")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient_rendez")
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultation_id")
    @JsonBackReference(value = "consultation_rendez")
    private Consultation consultation;
}
