package com.mbc.clickclinic.service;



import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface RendezvousService {
    Rendezvous saveRendezvous(Rendezvous rendezvous);
    void deleteRendezvous(Rendezvous rendezvous);
    Rendezvous updateRendezvous(Rendezvous rendezvous);
    List<Rendezvous> Rendezvouss();
    Rendezvous RendezvousById(Long id);
    Rendezvous RendezvousByDate(Date dateRv);

}
