package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Patient extends Personne{
    private String groupSanguin;

    //JsonManagedReference pour les Listes d'objets, BackReference pour les objets uniques.
    @ManyToOne
    @JoinColumn(name = "salle_id")
    @JsonBackReference(value = "salle_patient")
    private SalleDattente salleDattente;

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference(value = "patient_rendez")
    private List<Rendezvous> rendezvousList;

    public void add(Rendezvous rendezvous){
        if(this.rendezvousList == null){
            rendezvousList = new ArrayList<>();
        }
        rendezvousList.add(rendezvous);
    }

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference(value = "patient_certificat")
    private List<CertificatMedicale> certificatMedicaleList;

    public void add(CertificatMedicale certificatMedicale){
        if(this.certificatMedicaleList == null){
            certificatMedicaleList = new ArrayList<>();
        }
        certificatMedicaleList.add(certificatMedicale);
    }

    @OneToOne
    @JoinColumn(name = "dossier_id")
    private DossierMedicale dossierMedicale;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "demandeCert_id")
    private DemandeCertificat demandeCertificat;

}
