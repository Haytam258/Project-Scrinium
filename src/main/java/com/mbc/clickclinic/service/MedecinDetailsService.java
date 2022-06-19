package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.MedecinRepository;
import com.mbc.clickclinic.dao.PatientRepository;
import com.mbc.clickclinic.dao.PersonneRepository;
import com.mbc.clickclinic.dao.SecretaireRepository;
import com.mbc.clickclinic.entities.*;
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
    private final PersonneRepository personneRepository;

    @Autowired
    public MedecinDetailsService(MedecinRepository medecinRepository, PatientRepository patientRepository, SecretaireRepository secretaireRepository, PersonneRepository personneRepository){
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.secretaireRepository = secretaireRepository;
        this.personneRepository = personneRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Medecin medecin = medecinRepository.findMedecinByEmail(username);
        if(medecin == null){
            Patient patient = patientRepository.findPatientByEmail(username);
            if(patient == null){
                Secretaire secretaire = secretaireRepository.findSecretaireByEmail(username);
                if(secretaire == null){
                    Personne personne = personneRepository.findPersonneByEmail(username);
                    if(personne == null){
                        throw new UsernameNotFoundException("Invalid Login");
                    }
                    Collection<GrantedAuthority> grantedAuthorityCollection = new ArrayList<>();
                    grantedAuthorityCollection.add(new SimpleGrantedAuthority(personne.getRole()));
                    return new CustomUser(personne.getEmail(), personne.getPassword(),1 ,personne.getId() ,personne.getNom(),personne.getPrenom(),grantedAuthorityCollection);
                }
                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority(secretaire.getRole()));
                return new CustomUser(secretaire.getEmail(), secretaire.getPassword(),4,secretaire.getId() ,secretaire.getNom() ,secretaire.getPrenom() ,grantedAuthorities);
            }
            Collection<GrantedAuthority> authorityCollection = new ArrayList<>();
            authorityCollection.add(new SimpleGrantedAuthority(patient.getRole()));
            return new CustomUser(patient.getEmail(), patient.getPassword(),3, patient.getId(), patient.getNom(),patient.getPrenom(),authorityCollection);
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(medecin.getRole()));
        return new CustomUser(medecin.getEmail(), medecin.getPassword(),2, medecin.getId(), medecin.getNom() ,medecin.getPrenom(),authorities);
    }
}
