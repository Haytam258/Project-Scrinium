package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
public class CustomUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private final String username;
    private String password;
    private String nom;
    private String prenom;
    private Collection<? extends GrantedAuthority> authorities;
    private int type; // 1 : admin, 2 : médecin, 3 : patient, 4 : secrétaire
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public CustomUser(String username, String password, int type, Integer id ,String nom, String prenom, Collection<? extends GrantedAuthority> authorities){
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.authorities = authorities;
        this.prenom = prenom;
        this.nom = nom;
        this.username = username;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
