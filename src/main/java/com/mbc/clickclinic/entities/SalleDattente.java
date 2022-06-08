package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SalleDattente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @OneToMany
   // private Patient patient;

    private String heure = LocalTime.now().toString(); // Look for hour datetime

    @OneToMany(mappedBy = "salleDattente")
    @JsonManagedReference(value = "salle_patient")
    @ToString.Exclude
    private List<Patient> patientList;

    public void add(Patient patient){
        if(this.patientList == null){
            patientList = new ArrayList<>();
        }
        patientList.add(patient);
    }

    @OneToOne(mappedBy = "salleDattente")
    @JoinColumn(name = "medecin_id")
    @JsonBackReference(value = "medecin_salle")
    @ToString.Exclude
    private Medecin medecin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SalleDattente that = (SalleDattente) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
