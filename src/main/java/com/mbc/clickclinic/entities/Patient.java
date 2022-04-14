package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Patient extends Personne{
    private String groupSanguin;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    @JsonManagedReference
    private SalleDattente salleDattente;

    @OneToMany(mappedBy = "patient")
    @JsonBackReference
    private List<Rendezvous> rendezvousList;

    public void add(Rendezvous rendezvous){
        if(this.rendezvousList == null){
            rendezvousList = new ArrayList<>();
        }
        rendezvousList.add(rendezvous);
    }

    @OneToMany(mappedBy = "patient")
    @JsonBackReference
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

}
