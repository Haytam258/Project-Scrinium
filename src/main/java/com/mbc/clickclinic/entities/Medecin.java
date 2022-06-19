package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Medecin extends Personne {
    private String specialite; //profil

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "agenda_id")
    @JsonManagedReference(value = "medecin_agenda")
    private List<Agenda> agenda;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salle_id")
    @JsonManagedReference(value = "medecin_salle")
    @ToString.Exclude
    private SalleDattente salleDattente;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "medecin_rendez")
    @ToString.Exclude
    private List<Rendezvous> rendezvousList;

    public void add(Rendezvous rendezvous){
        if(this.rendezvousList == null){
            rendezvousList = new ArrayList<>();
        }
        rendezvousList.add(rendezvous);
    }

    public void remove(Agenda agenda1){
        this.agenda.remove(agenda1);
    }

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL )
    @JsonManagedReference(value = "medecin_certificat")
    @ToString.Exclude
    private List<CertificatMedicale> certificatMedicaleList;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "medecin_demande")
    @ToString.Exclude
    private List<DemandeCertificat> demandeCertificatList;

    public void remove(DemandeCertificat demandeCertificat){
        demandeCertificatList.remove(demandeCertificat);
    }

    @OneToMany( mappedBy = "medecin", cascade = CascadeType.ALL)
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
