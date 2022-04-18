package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class DemandeCertificat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int status; // Statut = 0 non traité, Statut = 1 acceptée (on ne va pas faire status 2, si elle est refusée elle sera automatiquement supprimée)

    //On précise les cascades car si on met cascade ALL, alors la suppression de la demande entrainera la suppression automatique du médecin et du patient associé.
    // (CascadeType.DELETE (REMOVE) est responsable de cela.
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient_demande")
    private Patient patient;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "medecin_id")
    @JsonBackReference(value = "medecin_demande")
    private Medecin medecin;
}
