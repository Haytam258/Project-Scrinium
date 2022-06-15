package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.MedecinRepository;
import com.mbc.clickclinic.dao.PatientRepository;
import com.mbc.clickclinic.dao.SecretaireRepository;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
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
public class MedecinDetailsService implements UserDetailsService {

    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository;
    private final SecretaireRepository secretaireRepository;
    @Autowired
    public MedecinDetailsService(MedecinRepository medecinRepository, PatientRepository patientRepository, SecretaireRepository secretaireRepository){
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.secretaireRepository = secretaireRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Medecin medecin = medecinRepository.findMedecinByEmail(username);
        if(medecin == null){
            Patient patient = patientRepository.findPatientByEmail(username);
            if(patient == null){
                Secretaire secretaire = secretaireRepository.findSecretaireByEmail(username);
                if(secretaire == null){
                    throw new UsernameNotFoundException("Invalid Login");
                }
                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority(secretaire.getRole()));
                return new User(secretaire.getEmail(), secretaire.getPassword(), grantedAuthorities);
            }
            Collection<GrantedAuthority> authorityCollection = new ArrayList<>();
            authorityCollection.add(new SimpleGrantedAuthority(patient.getRole()));
            return new User(patient.getEmail(), patient.getPassword(), authorityCollection);
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(medecin.getRole()));
        return new User(medecin.getEmail(), medecin.getPassword(), authorities);
    }
}
