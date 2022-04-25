package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor @ToString
public class DemandeCertificat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int status; // Statut = 0 non traité, Statut = 1 acceptée (on ne va pas faire status 2, si elle est refusée elle sera automatiquement supprimée)

    //On précise les cascades car si on met cascade ALL, alors la suppression de la demande entrainera la suppression automatique du médecin et du patient associé.
    // (CascadeType.DELETE (REMOVE) est responsable de cela.
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient_demande")
    private Patient patient;

    @ManyToOne
    @JsonBackReference(value = "medecin_demande")
    private Medecin medecin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DemandeCertificat that = (DemandeCertificat) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
