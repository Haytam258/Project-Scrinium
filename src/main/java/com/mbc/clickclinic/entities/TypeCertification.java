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
@AllArgsConstructor @ToString
public class TypeCertification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;

    @OneToMany(mappedBy = "typeCertification")
    @JsonManagedReference(value = "type_certificat")
    @ToString.Exclude
    private List<CertificatMedicale> certificatMedicaleList;

    public void add(CertificatMedicale certificatMedicale){
        if(this.certificatMedicaleList == null){
            certificatMedicaleList = new ArrayList<>();
        }
        certificatMedicaleList.add(certificatMedicale);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TypeCertification that = (TypeCertification) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
