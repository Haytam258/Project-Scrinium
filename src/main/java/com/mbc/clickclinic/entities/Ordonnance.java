package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Ordonnance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private List<Medicament> medicaments;
    private String posologie; //Nombre de géllule à prendre à la fois par exemple
    private int nbrFoisParJour;
    private String dureeTraitement;
    private int dose;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medicament_ordonance",
    joinColumns = @JoinColumn(name = "ordonnance_id"),
    inverseJoinColumns = @JoinColumn(name = "medicament_id"))
    @ToString.Exclude
    private List<Medicament> medicamentList;

    public void add(Medicament medicament){
        if(this.medicamentList == null ){
            medicamentList = new ArrayList<>();
        }
        medicamentList.add(medicament);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultation_id")
    @JsonBackReference(value = "consultation_ordonnance")
    private Consultation consultation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ordonnance that = (Ordonnance) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
