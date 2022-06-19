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
public class TypeMedicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String form;

    @OneToMany(mappedBy = "typeMedicament")
    @JsonManagedReference(value = "type_medicament")
    @ToString.Exclude
    private List<Medicament> medicamentList;

    public void add(Medicament medicament){
        if(this.medicamentList == null){
            medicamentList = new ArrayList<>();
        }
        medicamentList.add(medicament);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TypeMedicament that = (TypeMedicament) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
