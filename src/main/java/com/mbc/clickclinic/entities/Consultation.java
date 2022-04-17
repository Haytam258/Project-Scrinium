package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String motif; //Raison de la visite
    private String diagnostique;
    private String ResultatExmentClinique; //ResultatsDanalyses
    private String remarques;
    private double poids;
    private double talle;
    private double imc;
    private double temperature;
    private int frequenceCardiaque;
    private String pressionArterielle;

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    @JsonBackReference(value = "dossier_consultation")
    private DossierMedicale dossierMedicale;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="paiement_id")
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rendez_id")
    private Rendezvous rendezvous;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "certificat_id")
    private CertificatMedicale certificatMedicale;

}
