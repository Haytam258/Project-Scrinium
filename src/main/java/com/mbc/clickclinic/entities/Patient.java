package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor @ToString
public class Patient extends Personne{
    private String groupSanguin;
    //private LocalDate dateCreation = LocalDate.now();

    //JsonManagedReference pour les Listes d'objets, BackReference pour les objets uniques.
    @ManyToOne
    @JoinColumn(name = "salle_id")
    @JsonBackReference(value = "salle_patient")
    private SalleDattente salleDattente;

    @OneToMany(mappedBy = "patient", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @JsonManagedReference(value = "patient_rendez")
    @ToString.Exclude
    private List<Rendezvous> rendezvousList;

    public void add(Rendezvous rendezvous){
        if(this.rendezvousList == null){
            rendezvousList = new ArrayList<>();
        }
        rendezvousList.add(rendezvous);
    }

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference(value = "patient_certificat")
    @ToString.Exclude
    private List<CertificatMedicale> certificatMedicaleList;

    public void add(CertificatMedicale certificatMedicale){
        if(this.certificatMedicaleList == null){
            certificatMedicaleList = new ArrayList<>();
        }
        certificatMedicaleList.add(certificatMedicale);
    }

    @OneToOne
    @JoinColumn(name = "dossier_id")
    @JsonManagedReference(value = "patient_dossier")
    private DossierMedicale dossierMedicale;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "demandeCert_id")
    @JsonManagedReference(value = "patient_demande")
    private DemandeCertificat demandeCertificat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Patient patient = (Patient) o;
        return getId() != null && Objects.equals(getId(), patient.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
