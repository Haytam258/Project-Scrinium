package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.MedecinRepository;
import com.mbc.clickclinic.entities.Medecin;
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
public class MedecinDetailsService implements UserDetailsService {

    private final MedecinRepository medecinRepository;
    @Autowired
    public MedecinDetailsService(MedecinRepository medecinRepository){
        this.medecinRepository = medecinRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Medecin medecin = medecinRepository.findMedecinByEmail(username);
        if(medecin == null){
            return null;
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(medecin.getRole()));
        return new User(medecin.getEmail(), medecin.getPassword(), authorities);
    }
}
