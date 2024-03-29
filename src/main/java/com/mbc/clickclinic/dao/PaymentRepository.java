package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    public Payment findPaymentByConsultation(Consultation consultation);
}
