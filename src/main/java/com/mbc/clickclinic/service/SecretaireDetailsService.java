package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.SecretaireRepository;
import com.mbc.clickclinic.entities.Secretaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class SecretaireDetailsService implements UserDetailsService {

    private final SecretaireRepository secretaireRepository;

    @Autowired
    public SecretaireDetailsService(SecretaireRepository secretaireRepository){
        this.secretaireRepository = secretaireRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Secretaire secretaire = secretaireRepository.findSecretaireByEmail(username);
        if(secretaire == null){
            return null;
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(secretaire.getRole()));
        return new User(secretaire.getEmail(), secretaire.getPassword(), authorities);
    }
}
