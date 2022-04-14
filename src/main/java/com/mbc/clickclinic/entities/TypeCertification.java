package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class TypeCertification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    @OneToMany(mappedBy = "typeCertification")
    @JsonBackReference
    private List<CertificatMedicale> certificatMedicaleList;

    public void add(CertificatMedicale certificatMedicale){
        if(this.certificatMedicaleList == null){
            certificatMedicaleList = new ArrayList<>();
        }
        certificatMedicaleList.add(certificatMedicale);
    }
}
