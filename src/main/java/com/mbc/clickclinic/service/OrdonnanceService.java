package com.mbc.clickclinic.service;



import com.mbc.clickclinic.entities.Ordonnance;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface OrdonnanceService {
    Ordonnance saveOrdonnance(Ordonnance ordonnance);
    void deleteOrdonnance(Ordonnance ordonnance);
    Ordonnance updateOrdonnance(Ordonnance ordonnance);
    List<Ordonnance> Ordonnances();
    Ordonnance OrdonnanceById(Long id);
}
