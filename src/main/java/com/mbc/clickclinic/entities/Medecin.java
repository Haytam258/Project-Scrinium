package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Medecin extends Personne {
    private String specialite; //profil

    @OneToOne
    @JoinColumn(name = "agenda_id")
    @JsonManagedReference(value = "medecin_agenda")
    private Agenda agenda;

    @OneToOne
    @JoinColumn(name = "salle_id")
    @JsonManagedReference(value = "medecin_salle")
    private SalleDattente salleDattente;

    @OneToMany(mappedBy = "medecin")
    @JsonManagedReference(value = "medecin_rendez")
    private List<Rendezvous> rendezvousList;

    public void add(Rendezvous rendezvous){
        if(this.rendezvousList == null){
            rendezvousList = new ArrayList<>();
        }
        rendezvousList.add(rendezvous);
    }

    @OneToMany(mappedBy = "medecin")
    @JsonManagedReference(value = "medecin_certificat")
    private List<CertificatMedicale> certificatMedicaleList;

    @OneToMany(mappedBy = "medecin")
    @JsonManagedReference(value = "medecin_demande")
    private List<DemandeCertificat> demandeCertificatList;
}
