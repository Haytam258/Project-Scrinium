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
@AllArgsConstructor
public class Ordonnance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private List<Medicament> medicaments;

    @OneToMany(mappedBy = "ordonnance", cascade = CascadeType.ALL)
    @JsonManagedReference("ordonnance_items")
    @ToString.Exclude
    private List<OrdonnanceItems> ordonnanceItemsList;

    public void add(OrdonnanceItems ordonnanceItems){
        if(this.ordonnanceItemsList == null ){
            ordonnanceItemsList = new ArrayList<>();
        }
        ordonnanceItemsList.add(ordonnanceItems);
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
