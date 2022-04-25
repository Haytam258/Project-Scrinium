package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@AllArgsConstructor @ToString
public class DossierMedicale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String antecedent; //billan de maladie
    private String observations;

    @OneToMany(mappedBy = "dossierMedicale")
    @JsonManagedReference(value = "dossier_consultation")
    @ToString.Exclude
    private List<Consultation> consultationList;

    public void add(Consultation consultation){
        if(this.consultationList == null){
            consultationList = new ArrayList<>();
        }
        consultationList.add(consultation);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient_dossier")
    private Patient patient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DossierMedicale that = (DossierMedicale) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
