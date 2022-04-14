package com.mbc.clickclinic.service;


import com.mbc.clickclinic.entities.SalleDattente;
import org.springframework.stereotype.Service;


public interface SalleDattenteService {
    SalleDattente saveSalleDattente(SalleDattente notification);
    void deleteSalleDattente(SalleDattente notification);
}
