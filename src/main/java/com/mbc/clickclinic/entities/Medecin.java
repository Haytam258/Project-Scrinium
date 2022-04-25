package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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
    @ToString.Exclude
    private List<Rendezvous> rendezvousList;

    public void add(Rendezvous rendezvous){
        if(this.rendezvousList == null){
            rendezvousList = new ArrayList<>();
        }
        rendezvousList.add(rendezvous);
    }

    @OneToMany(mappedBy = "medecin")
    @JsonManagedReference(value = "medecin_certificat")
    @ToString.Exclude
    private List<CertificatMedicale> certificatMedicaleList;

    @OneToMany(mappedBy = "medecin")
    @JsonManagedReference(value = "medecin_demande")
    @ToString.Exclude
    private List<DemandeCertificat> demandeCertificatList;

    @OneToMany( mappedBy = "medecin")
    @JsonManagedReference(value = "conges_medecin")
    @ToString.Exclude
    private List<Conges> conges;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Medecin medecin = (Medecin) o;
        return getId() != null && Objects.equals(getId(), medecin.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
