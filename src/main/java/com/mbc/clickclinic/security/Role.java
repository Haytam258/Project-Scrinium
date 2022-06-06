package com.mbc.clickclinic.security;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_role;

    @Enumerated(EnumType.STRING)
    private GeneralRole roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id_role != null && Objects.equals(id_role, role.id_role);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
