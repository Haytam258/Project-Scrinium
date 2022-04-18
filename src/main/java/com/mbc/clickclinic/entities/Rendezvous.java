package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor

//On utilise JsonIdentityInfo si on veut avoir une bi-directionnelle relation et voir les entités dans le JSON renvoyé par Postman
//Si on ne veut pas, le BackReference marchera toujours, on ne va tout simplement pas voir sur Rendezvous le medecin et le patient, mais ils existent toujours dans l'objet
//rendezvous.
public class Rendezvous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateRv;
    private String heure;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    @JsonBackReference(value = "medecin_rendez")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Medecin.class)
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient_rendez")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Patient.class)
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultation_id")
    @JsonBackReference(value = "consultation_rendez")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Consultation.class)
    private Consultation consultation;
}
