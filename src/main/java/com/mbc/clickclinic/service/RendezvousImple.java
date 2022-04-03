package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.RendezvousRepository;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class RendezvousImple implements RendezvousService{

    private final RendezvousRepository rendezvousRepository;

    @Autowired
    public RendezvousImple(RendezvousRepository rendezvousRepository){
        this.rendezvousRepository = rendezvousRepository;
    }
    @Override
    public Rendezvous saveRendezvous(Rendezvous rendezvous) {
        return rendezvousRepository.saveAndFlush(rendezvous);
    }

    @Override
    public void deleteRendezvous(Rendezvous rendezvous) {
        rendezvousRepository.delete(rendezvous);
    }

    @Override
    public Rendezvous updateRendezvous(Rendezvous rendezvous) {
        return null;
    }

    @Override
    public List<Rendezvous> Rendezvouss() {
        return rendezvousRepository.findAll();
    }

    @Override
    public Rendezvous RendezvousById(Long id) {
        return rendezvousRepository.findById(id).get();
    }

    @Override
    public Rendezvous RendezvousByDate(Date dateRv) {
        return rendezvousRepository.findRendezvousByDateRv(dateRv);
    }
}
