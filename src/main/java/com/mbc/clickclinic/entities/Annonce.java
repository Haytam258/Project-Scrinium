package com.mbc.clickclinic.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Annonce {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String objet;
    private String message;
    private Date dateCreation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Annonce annonce = (Annonce) o;
        return id != null && Objects.equals(id, annonce.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
