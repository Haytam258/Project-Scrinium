package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.PatientRepository;
import com.mbc.clickclinic.entities.Patient;
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
public class PatientDetailsService implements UserDetailsService {

    private final PatientRepository patientRepository;
    @Autowired
    public PatientDetailsService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findPatientByEmail(username);
        if(patient == null){
            return null;
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(patient.getRole()));
        return new User(patient.getEmail(), patient.getPassword(), authorities);
    }
}
